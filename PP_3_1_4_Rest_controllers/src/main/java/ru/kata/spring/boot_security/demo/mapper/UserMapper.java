package ru.kata.spring.boot_security.demo.mapper;

import org.mapstruct.Mapper;
import ru.kata.spring.boot_security.demo.dto.UserDto;
import ru.kata.spring.boot_security.demo.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User source);
    User toModel(UserDto source);
}