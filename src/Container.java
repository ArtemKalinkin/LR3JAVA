import java.util.ArrayList;
import java.util.List;

public class Container<T extends Printable> {
    private List<T> elements;

    public List<T> getElements() {
        return elements;
    }

    public void setElements(List<T> elements) {
        this.elements = elements;
    }

    public Container() {
        this.elements = new ArrayList<>();
    }

    public void addElement(T element) {
        elements.add(element);
    }

    public void removeElement(T element) {
        elements.remove(element);
    }

    public void displayElements() {
        for (T element : elements) {
            System.out.println(element.getFormattedInfo());
        }
    }
}
