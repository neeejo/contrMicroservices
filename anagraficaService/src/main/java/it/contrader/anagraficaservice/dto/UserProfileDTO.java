package it.contrader.anagraficaservice.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDTO {

    private Long id;

    private String username;

    private List<String> roles;
}
