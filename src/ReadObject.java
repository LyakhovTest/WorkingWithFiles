import java.io.*;

public class ReadObject {
    public static void read() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("people.bin"))) {
            Person pers = (Person) ois.readObject();
            System.out.println(pers);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
