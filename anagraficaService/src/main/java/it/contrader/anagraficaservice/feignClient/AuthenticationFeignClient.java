package it.contrader.anagraficaservice.feignClient;

import it.contrader.anagraficaservice.dto.UserDTO;
import it.contrader.anagraficaservice.feignClient.FeigClientConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="authservice", configuration = FeigClientConfig.class, fallback = AuthenticationFallback.class)
public interface AuthenticationFeignClient {
    @GetMapping("user/getallusers")
    public List<UserDTO> getAllUsers();
}

@Slf4j
@Component
class AuthenticationFallback implements FallbackFactory<AuthenticationFeignClient> {

    @Override
    public AuthenticationFeignClient create(Throwable cause) {
        return new AuthenticationFeignClient() {

            @Override
            public List<UserDTO> getAllUsers() {
                throw new RuntimeException("Errore durante la lettura della lista utenti - ERROR: " + cause);
            }
        };
    }
}
