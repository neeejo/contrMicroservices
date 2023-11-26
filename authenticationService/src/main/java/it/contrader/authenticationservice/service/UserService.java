package it.contrader.authenticationservice.service;

import feign.FeignException;
import it.contrader.authenticationservice.customException.CustomFeignException;
import it.contrader.authenticationservice.customException.UsernameAlreadyInUseException;
import it.contrader.authenticationservice.dto.*;
import it.contrader.authenticationservice.feignClient.AnagraficaFeignClient;
import it.contrader.authenticationservice.mapper.UserMapper;
import it.contrader.authenticationservice.model.Role;
import it.contrader.authenticationservice.model.User;
import it.contrader.authenticationservice.repository.UserRepository;
import it.contrader.authenticationservice.security.JwtUtils;
import it.contrader.authenticationservice.security.UserDetailsImpl;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.lang.model.type.UnionType;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private AnagraficaFeignClient anagraficaFeignClient;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    public LoggedUserDTO login(LoginDTO loginDTO) {

        Authentication authentication = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwt(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());

        return new LoggedUserDTO(userDetails.getId(),
            userDetails.getUsername(),

            jwt,
            roles);
    }

    public void registerUser(SignupDTO signUpRequest) throws UsernameAlreadyInUseException {

       if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new UsernameAlreadyInUseException("Error: Username is already taken!");
        }

        Set<Role> savedRoles = authService.createRoles(signUpRequest.getRoles());
        Role adminRole = authService.readRole(Role.ERole.ROLE_ADMIN).orElse(new Role());

        User savedUser = userRepository.save(User.builder()
            .username(signUpRequest.getUsername())
            .password(encoder.encode(signUpRequest.getPassword()))
            .roles(savedRoles)
            .build());

        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(savedUser.getUsername());

        jwtUtils.setRequestJwt(jwtUtils.generateJwt(userDetails));

        AnagraficaDTO savedAnagrafica;
        try {

            signUpRequest.getAnagrafica().setIduser(savedUser.getId());
            savedAnagrafica = anagraficaFeignClient.register(signUpRequest.getAnagrafica());

        } catch (FeignException e) {
            throw new CustomFeignException("Errore durante la registrazione dell'anagrafica", e);
        }

        if(savedRoles.contains(adminRole)) {
            try {
                signUpRequest.getNegozio().setProprietario(savedAnagrafica);
                anagraficaFeignClient.registerNegozio(signUpRequest.getNegozio());
            } catch (FeignException e) {
                throw new CustomFeignException("Errore durante la registrazione del negozio", e);
            }
        }

    }
    public UserDTO read(Long id) {
        return mapper.toUserDTO(userRepository.findById(id).orElseThrow(()->new RuntimeException("errore lettura user")));
    }

    public UserDTO insert(UserDTO userDTO) {
        return mapper.toUserDTO(userRepository.save(mapper.toUser(userDTO)));
    }



    public LoggedUserDTO update(UserDTO userDTO) {
        UserDTO user = read(userDTO.getId());
        if(userDTO.getUsername()!=null) user.setUsername(userDTO.getUsername());
        if(userDTO.getPassword()!=null && !userDTO.getPassword().isEmpty()) user.setPassword(encoder.encode(userDTO.getPassword()));
        User us=  userRepository.save(mapper.toUser(user));

        String jwt= jwtUtils.generateJwt((UserDetailsImpl) userDetailsService.loadUserByUsername(us.getUsername()));

        List<String> roles = new ArrayList<>();
        for(Role role : user.getRoles()) {
            roles.add(role.getRole().toString());
        }
        return new LoggedUserDTO(
                us.getId(),
                us.getUsername(),
                jwt,
                roles);
    }

    public void delete(Long id) {userRepository.deleteById(id);}

    public List<UserProfileDTO> getAllUsers() {
        List<User> userList = userRepository.findByRolesRole(Role.ERole.ROLE_USER);
        List<UserProfileDTO> userDTOList = new ArrayList<>();
        for(User user : userList) {
            UserProfileDTO newUser = new UserProfileDTO();
            newUser.setId(user.getId());
            newUser.setUsername(user.getUsername());
            List<String> roles = new ArrayList<>();
            for(Role role : user.getRoles()) {
                roles.add(role.getRole().toString());
            }
            newUser.setRoles(roles);
            userDTOList.add(newUser);
        }
        System.out.println(userDTOList);
        return userDTOList;
    }

    public ProfileListDTO getAllProfile() {
        List<UserProfileDTO> userList = this.getAllUsers();
        return anagraficaFeignClient.getAllProfiles(userList);
    }

    public ProfileDTO getUserProfile(Long id) {
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userProfileDTO.setId(user.getId());
        userProfileDTO.setUsername(user.getUsername());
        List<String> roles = new ArrayList<>();
        user.getRoles().forEach((role -> roles.add(role.getRole().toString())));
        userProfileDTO.setRoles(roles);
        return anagraficaFeignClient.getProfile(userProfileDTO);
    }


}
