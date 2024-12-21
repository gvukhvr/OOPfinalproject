package utilites;
import java.io.FileNotFoundException;

public interface Observable {
    void subscribe(Observer observer);
    void unsubscribe(Observer observer);
    void notifyObservers() throws FileNotFoundException;
}