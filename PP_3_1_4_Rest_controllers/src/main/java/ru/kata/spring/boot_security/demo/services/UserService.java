package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.dto.UserDto;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserDto> getAllUsers();

    void saveUser(User user);

    UserDto findUserById (long id);

    void deleteUser (long id);

    void updateUser (long id, User user);

}