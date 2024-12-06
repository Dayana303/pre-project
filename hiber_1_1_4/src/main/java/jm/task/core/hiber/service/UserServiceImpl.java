package jm.task.core.hiber.service;

import jm.task.core.hiber.dao.UserDao;
import jm.task.core.hiber.dao.UserDaoHibernateImpl;
import jm.task.core.hiber.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao dao = new UserDaoHibernateImpl();

    public void createUsersTable() {
        dao.createUsersTable();
    }

    public void dropUsersTable() {
        dao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        dao.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        dao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    public void cleanUsersTable() {
        dao.cleanUsersTable();
    }
}