package it.contrader.authenticationservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfileListDTO {
    Long totalElements;
    Long size;
    Integer pageNumber;
    List<List<ProfileDTO>> content;
}
