package web.model;

public class Car {

    private String model;
    private String color;
    private String speed;

    public Car(String model, String color, String speed) {
        this.model = model;
        this.color = color;
        this.speed = speed;
    }

    public Car() {
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", speed='" + speed + '\'' +
                '}';
    }
}