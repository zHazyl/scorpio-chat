package com.tnh.authservice.mapper;

import com.tnh.authservice.domain.User;
import com.tnh.authservice.dto.UserDTO;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserMapper {

//    @Mapping(target = "id", expression = "java(convertIdToString(user.getId()))")
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "firstName", source = "first_name")
    @Mapping(target = "lastName", source = "last_name")
    @Mapping(target = "email", source = "email")
    UserDTO mapToUserDTO(User user);

    @InheritConfiguration(name = "mapToUserDTO")
//    @Mapping(target = "activationKey", ignore = true)
    UserDTO mapToUserDTOWithoutActivationKey(User user);

    default String convertIdToString(UUID id) {
        if (id == null) {
            return null;
        }
        return id.toString();
    }


}
