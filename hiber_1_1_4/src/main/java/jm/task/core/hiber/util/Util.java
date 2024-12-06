package jm.task.core.hiber.util;


import jm.task.core.hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/pre_project";
    private static final String USER = "root";
    private static final String PASSWORD = "di1701";

    private static Connection connection;

    private static SessionFactory sessionFactory;

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        }
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Properties properties = new Properties();
            properties.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
            properties.put(Environment.URL, DB_URL);
            properties.put(Environment.USER, USER);
            properties.put(Environment.PASS, PASSWORD);
            properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
            properties.put(Environment.SHOW_SQL, "true");

            sessionFactory = new Configuration()
                    .addAnnotatedClass(User.class)
                    .setProperties(properties)
                    .buildSessionFactory();
        }
        return sessionFactory;
    }


}
