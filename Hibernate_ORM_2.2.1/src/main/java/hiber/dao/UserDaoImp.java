package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {


    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory factory) {
        this.sessionFactory = factory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public User getUserByModelAndSeries (String model, int series) {

        TypedQuery<User> userQuery = sessionFactory.getCurrentSession()
                .createQuery("SELECT u FROM User u INNER JOIN FETCH u.car c WHERE c.model = :model AND c.series = :series", User.class);

        userQuery.setParameter("model", model);
        userQuery.setParameter("series", series);

        return userQuery.getSingleResult();
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers(){
        return sessionFactory.getCurrentSession().createQuery("from User").getResultList();

    }
}