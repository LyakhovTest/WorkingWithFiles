import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class WriteObject {
    public static void write(){
        Car car = new Car("lada", "granta", 5,null);
        Person person = new Person("Goga", 37, car);
        car.setPerson(person);
        person.setCar(car);

        FileOutputStream fos = null;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("people.bin"))){
            oos.writeObject(person);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
