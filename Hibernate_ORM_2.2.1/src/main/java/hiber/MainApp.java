package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);

        Car car1 = new Car("Car1", 1);
        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        car1.setUser(user1);
        userService.add(user1);
        carService.add(car1);

        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        Car car2 = new Car("Car2", 2);
        car2.setUser(user2);
        userService.add(user2);
        carService.add(car2);

        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        Car car3 = new Car("Car3", 3);
        car3.setUser(user3);
        userService.add(user3);
        carService.add(car3);

        User user4 = new User("User4", "Lastname4", "user4@mail.ru");
        Car car4 = new Car("Car4", 4);
        car4.setUser(user4);
        userService.add(user4);
        carService.add(car4);


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("*******");
        }

        List<Car> cars = carService.listCars();
        for (Car car : cars) {
            System.out.println("Serial = " + car.getSeries());
            System.out.println("Model = " + car.getModel());
        }

        System.out.println(userService.getUserByModelAndSeries("Car4", 4));

        System.out.println(carService.getCarByUser(user1));

        context.close();
    }
}