package es.codeurjc.booksmanagementspring.mapper;

import es.codeurjc.booksmanagementspring.dto.UserCreateDTO;
import es.codeurjc.booksmanagementspring.dto.UserDTO;
import es.codeurjc.booksmanagementspring.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ReviewMapper.class})
public interface UserMapper {

    UserDTO toDTO(User user);

    User toDomain(UserDTO userDTO);

    User toDomain(UserCreateDTO userDTO);
}
