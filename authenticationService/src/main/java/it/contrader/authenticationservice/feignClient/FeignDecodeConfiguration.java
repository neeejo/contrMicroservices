package it.contrader.authenticationservice.feignClient;

import org.springframework.cloud.openfeign.support.PageJacksonModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignDecodeConfiguration {
        @Bean
        public PageJacksonModule pageJacksonModule() {
            return new PageJacksonModule();
        }

}
