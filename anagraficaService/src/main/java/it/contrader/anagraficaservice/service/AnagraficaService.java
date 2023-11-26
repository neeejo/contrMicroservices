package it.contrader.anagraficaservice.service;

import it.contrader.anagraficaservice.dto.*;
import it.contrader.anagraficaservice.feignClient.AuthenticationFeignClient;
import it.contrader.anagraficaservice.mapper.AnagraficaMapper;
import it.contrader.anagraficaservice.model.Anagrafica;
import it.contrader.anagraficaservice.repository.AnagraficaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AnagraficaService {

    @Autowired
    private AnagraficaRepository repository;
    @Autowired
    private AuthenticationFeignClient feignClient;

    @Autowired
    private AnagraficaMapper mapper;

    public AnagraficaDTO save(AnagraficaDTO anagraficaDTO) {
        return mapper.toAnagraficaDTO(repository.save(mapper.toAnagrafica(anagraficaDTO)));
    }

    public AnagraficaDTO findByIduser(Long id) {
        return mapper.toAnagraficaDTO((repository.findByIduser(id)));
    }

    public AnagraficaDTO updateAnagrafica(AnagraficaDTO anagraficaDTO){
        return mapper.toAnagraficaDTO(repository.save(mapper.toAnagrafica(anagraficaDTO)));
    }

    public void deleteAnagrafica(Long idanagrafica){
        repository.deleteById(idanagrafica);
    }

    public AnagraficaDTO readAnagrafica(Long idanagrafica){
        return mapper.toAnagraficaDTO(repository.findById(idanagrafica).orElseThrow(()->new RuntimeException("errore lettura anagrafica")));
    }

    public Page<Anagrafica> getAllPaginata(Pageable pageable) { return ((AnagraficaRepository) repository).findAllByIduserNotNull(pageable); }

    public ProfileListDTO getAllProfile(List<UserDTO> userDTOList) {
        ProfileListDTO profileList = new ProfileListDTO();
        profileList.setSize(5L);
        profileList.setPageNumber(0);
        profileList.setTotalElements((long) userDTOList.size());
        profileList.setContent(new ArrayList<>());
        List<ProfileDTO> currentPage = new ArrayList<>();
        for(UserDTO u : userDTOList) {
            if (currentPage.size() == profileList.getSize()) {
                profileList.getContent().add(new ArrayList<>(currentPage));
                System.out.println(currentPage.size());
                currentPage.clear();
            }
            ProfileDTO profileDTO = new ProfileDTO();
            profileDTO.setUser(u);
            profileDTO.setAnagrafica(mapper.toAnagraficaDTO(repository.findByIduser(u.getId())));
            currentPage.add(profileDTO);
        }
        profileList.getContent().add(new ArrayList<>(currentPage));
        System.out.println(profileList);
        //Pageable pageable = PageRequest.of(0,5, Sort.by(Sort.Direction.ASC, "username"));
        //return new PageImpl<ProfileDTO>(profileList, pageable, profileList.size());

        return profileList;
    }

    public ProfileDTO getProfile(UserDTO userProfileDTO) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setUser(userProfileDTO);
        profileDTO.setAnagrafica(this.findByIduser(userProfileDTO.getId()));
        return profileDTO;
    }
}
