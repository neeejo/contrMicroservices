package it.contrader.authenticationservice.feignClient;

import it.contrader.authenticationservice.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "anagservice", configuration = FeigClientConfig.class)
public interface AnagraficaFeignClient {

    @PostMapping("/anag/registerAnagrafica")
    AnagraficaDTO register(@RequestBody AnagraficaDTO anagraficaDTO);

    @PostMapping("/neg/registerNegozio")
    NegozioDTO registerNegozio(@RequestBody NegozioDTO negozioDTO);

    @GetMapping("anag/findByIduser")
    AnagraficaDTO findByIduser(@RequestParam Long id);

    @PostMapping("anag/getProfile")
    ProfileDTO getProfile(@RequestBody UserProfileDTO userProfileDTO);

    @PostMapping("anag/getAllProfiles")
    ProfileListDTO getAllProfiles(@RequestBody List<UserProfileDTO> userDTOList);

}

@Slf4j
@Component
class AnagraficaFallback implements FallbackFactory<AnagraficaFeignClient> {

    @Override
    public AnagraficaFeignClient create(Throwable cause) {
        return new AnagraficaFeignClient() {
            @Override
            public AnagraficaDTO register(AnagraficaDTO anagraficaDTO) {
                throw new RuntimeException("Errore durante la creazione dell'anagrafica - ERROR: " + cause);
            }

            @Override
            public NegozioDTO registerNegozio(NegozioDTO negozioDTO) {
                throw new RuntimeException("Errore durante la creazione del negozio - ERROR: " + cause);
            }

            @Override
            public AnagraficaDTO findByIduser(Long id) {
                throw  new RuntimeException("errore" + cause);
            }

            @Override
            public ProfileListDTO getAllProfiles(List<UserProfileDTO> userDTOList) {
                throw new RuntimeException("errore lista profili" + cause);
            }

            @Override
            public ProfileDTO getProfile( UserProfileDTO userProfileDTO){
                throw new RuntimeException("errore lettura profilo" + cause);
            };
        };

    }
}

