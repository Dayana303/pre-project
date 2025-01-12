package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.mapper.UserMapper;
import ru.kata.spring.boot_security.demo.dto.UserDto;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.RoleSevice;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/api")
public class RESTController {

    private final UserService userService;
    private final RoleSevice roleSevice;
    private final UserMapper userMapper;

    @Autowired
    public RESTController(UserService userService, RoleSevice roleSevice, UserMapper userMapper) {
        this.userService = userService;
        this.roleSevice = roleSevice;
        this.userMapper = userMapper;
    }

    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @GetMapping("/currentuser")
    public ResponseEntity<UserDto> getCurrentUser(Principal principal) {
        return new ResponseEntity<>(userMapper.toDto((User) userService.loadUserByUsername(principal.getName())), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<UserDto> create(@RequestBody UserDto user) {
        userService.saveUser(userMapper.toModel(user));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<UserDto> update(@RequestBody UserDto user, @PathVariable("id") Long id) {
        userService.updateUser(id, userMapper.toModel(user));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>("Delete success",HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoles() {
        return new ResponseEntity<>(roleSevice.getAllRoles(), HttpStatus.OK);
    }

}