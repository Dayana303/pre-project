package jm.task.core.hiber.dao;

import jm.task.core.hiber.model.User;
import jm.task.core.hiber.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UserDaoHibernateImpl implements UserDao {


    private static final String CREATE_TABLE = """
            CREATE TABLE IF NOT EXISTS Users (
                Id INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(100),
                lastName VARCHAR(100),
                age TINYINT
            )""";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS Users";
    private static final String CLEAN_USERS_TABLE = "DELETE FROM Users";
    private static final Logger logger = Logger.getLogger(UserDaoHibernateImpl.class.getName());

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(CREATE_TABLE).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.log(Level.WARNING, "Error creating table: " + e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(DROP_TABLE).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.log(Level.WARNING, "Error when deleting table: " + e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User newUser = new User(name, lastName, age);
            session.save(newUser);
            transaction.commit();
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
            logger.log(Level.WARNING, "Error when saving user: " + e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        User user;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            user = session.find(User.class, id);
            if (user != null) {
                session.remove(user);
            } else {
                System.out.println("User with id " + id + " not found.");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.log(Level.WARNING, "Error when deleting user: " + e.getMessage());

        }
    }

    @Override
    public List<User> getAllUsers() {

        try (Session session = Util.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).list();
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error getting all users: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(CLEAN_USERS_TABLE).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.log(Level.WARNING, "Error when clearing table: " + e.getMessage());
        }
    }
}