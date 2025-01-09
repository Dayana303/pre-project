package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.model.dao.Role;

import java.util.List;

public interface RoleSevice {
    List<Role> getAllRoles();
}