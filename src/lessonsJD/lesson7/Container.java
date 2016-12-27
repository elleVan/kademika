package lessonsJD.lesson7;

import java.util.ArrayList;
import java.util.List;

public class Container<J> {

    private List<J> list = new ArrayList<J>();

    public Container() {
    }

    public void add(J element) {
        list.add(element);
    }

    public J get(int i) {
        return list.get(i);
    }

    public void remove(J element) {
        list.remove(element);
    }
}
