import java.util.List;

public interface Modifiable<T extends Modifiable<T>> {
    void changeFields(List<T> objectList);


}
