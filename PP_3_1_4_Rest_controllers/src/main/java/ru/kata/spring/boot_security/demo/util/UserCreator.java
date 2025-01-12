package ru.kata.spring.boot_security.demo.util;


import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public class UserCreator {


    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private static final String ROLE_USER = "ROLE_USER";


    public static List<User> createUsersWithRoles() {
        Role userRole = new Role();
        userRole.setName(ROLE_USER);
        Role adminRole = new Role();
        adminRole.setName(ROLE_ADMIN);
        User user = new User("user", "user", "kataStudent@mail.com", List.of(userRole));
        User admin = new User("admin", "admin", "kataStudentAdmin@mail.com", List.of(adminRole));
        return List.of(user, admin);
    }
}