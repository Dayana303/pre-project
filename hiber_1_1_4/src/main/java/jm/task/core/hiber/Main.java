package jm.task.core.hiber;

import jm.task.core.hiber.service.UserService;
import jm.task.core.hiber.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService service = new UserServiceImpl();
        service.createUsersTable();
        service.saveUser("User1", "User1", (byte) 27);
        service.saveUser("User2", "User2", (byte) 24);
        service.saveUser("User3", "User3", (byte) 50);
        service.saveUser("User4", "User4", (byte) 47);
        System.out.println(service.getAllUsers());
        service.cleanUsersTable();
        service.dropUsersTable();
    }
}