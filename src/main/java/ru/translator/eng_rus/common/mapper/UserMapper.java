package ru.translator.eng_rus.common.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.translator.eng_rus.User.DTO.UserDTO;
import ru.translator.eng_rus.User.Entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "nickname", source = "nickname")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "role", source = "role")
    @Mapping(target = "roleName", source = "roleName")
    UserDTO convertorEntityToDTO(User user);

    @Mapping(target = "nickname", source = "nickname")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "role", source = "role")
    @Mapping(target = "roleName", source = "roleName")
    User convertorDTOToEntity(UserDTO userDTO);
}
