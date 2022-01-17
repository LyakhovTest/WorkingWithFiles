import java.io.Serializable;

public class Car implements Serializable {
    private String brand;
    private String model;
    private transient int countOfDoors;
    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Car(String brand, String model, int countOfDoors, Person person) {
        this.brand = brand;
        this.model = model;
        this.countOfDoors = countOfDoors;
        this.person = person;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCountOfDoors() {
        return countOfDoors;
    }

    public void setCountOfDoors(int countOfDoors) {
        this.countOfDoors = countOfDoors;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", countOfDoors=" + countOfDoors +
                '}';
    }
}
