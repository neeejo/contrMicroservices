package it.contrader.authenticationservice.controller;

import it.contrader.authenticationservice.dto.*;
import it.contrader.authenticationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/readUser")
    public UserDTO readUser(@RequestParam Long id) {
        return service.read(id);
    }

    @PostMapping("/insertUser")
    public UserDTO insertUser(@RequestBody UserDTO userDTO) {
        return service.insert(userDTO);
    }

    @PutMapping("/updateUser")
    public LoggedUserDTO updateUser(@RequestBody UserDTO userDTO) {
        return service.update(userDTO);
    }

    @DeleteMapping("/deleteUser")
    public void deleteUser(@RequestParam Long id) {service.delete(id);
    }

    @GetMapping("/getallusers")
    public List<UserProfileDTO> getAllUsers() { return service.getAllUsers();}

    @GetMapping("/getallprofiles")
    public ProfileListDTO getAllProfile() { return service.getAllProfile();}

    @GetMapping("/getUserProfile")
    public ProfileDTO getProfile(@RequestParam Long id) { return service.getUserProfile(id);}
}
