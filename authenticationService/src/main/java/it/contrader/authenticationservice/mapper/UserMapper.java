package it.contrader.authenticationservice.mapper;
import it.contrader.authenticationservice.dto.UserDTO;
import it.contrader.authenticationservice.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface UserMapper {
    User toUser(UserDTO userDTO);

    UserDTO toUserDTO(User user);


}
