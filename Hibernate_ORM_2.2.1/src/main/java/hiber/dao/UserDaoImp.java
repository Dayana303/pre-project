package hiber.dao;

import hiber.model.Car;
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
    public User getUser(String model, int series) {

        TypedQuery<Car> carQuery = sessionFactory
                .getCurrentSession()
                .createQuery("from Car c where c.model=:model and c.series=:series", Car.class);

        carQuery.setParameter("model", model);
        carQuery.setParameter("series", series);

        Car car = carQuery.getSingleResult();

        TypedQuery<User> userQuery = sessionFactory.getCurrentSession().createQuery("from User u where u.id=:id");
        userQuery.setParameter("id", car.getId());

        return userQuery.getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers(){
        return sessionFactory.getCurrentSession().createQuery("from User").getResultList();

    }
}