package web.service;

import web.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void saveUser(User user);

    User findUserById (long id);

    void deleteUser (long id);

    void updateUser (long id, User user);

}
