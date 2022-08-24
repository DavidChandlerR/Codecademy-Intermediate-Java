import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Car implements Serializable {
    //Creating Car class instance fields
    private String make;
    private int year;
    //Creating cerializable interface
    private static final long serialVersionUID = 1L;

    public Car (String make, int year) {
        //Assigning parameters to Car class and initializing the member fields.
        this.make = make;
        this.year = year;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        //Creating 2 new Car objects
        Car toyota = new Car("Toyota", 2021);
        Car honda = new Car("Honda", 2020);
        //initialized a object in main which creates a stream of bytes from the JVM, to a txt file called persons.
        FileOutputStream fileOutputStream = new FileOutputStream("cars.txt");
        //Serializes an object in main which serializes an object to an output stream.
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(toyota);
        objectOutputStream.writeObject(honda);
    }
}