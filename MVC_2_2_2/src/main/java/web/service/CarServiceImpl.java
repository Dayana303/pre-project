package web.service;

import org.springframework.stereotype.Service;
import web.model.Car;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private static final List<Car> cars = List.of(
            new Car("BMW", "black", "75"),
            new Car("LADA", "red", "30"),
            new Car("SUZUKI", "green", "55"),
            new Car("KIA", "gray", "45"),
            new Car("RENO", "white", "60")
    );


    @Override
    public List<Car> getCars(int count) {
        if (count >= 5){
            return cars;
        }
        return cars.stream().limit(count).toList();
    }
}