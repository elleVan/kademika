package lessonsJD.lesson7.f_7_1;

import java.util.ArrayList;
import java.util.List;

public class Container<J extends Drink> {

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

    public void sort() {
        for (int i = 0; i < list.size(); i++) {
            boolean change = false;
            for (int j = 1; j < list.size() - i; j++) {
                if (list.get(j - 1).getPrice() > list.get(j).getPrice()) {
                    J element = list.get(j);
                    list.set(j, list.get(j - 1));
                    list.set(j - 1, element);
                    change = true;
                }
            }
            if (!change) {
                break;
            }
        }
    }

    public List<J> getAll() {
        return new ArrayList<J>(list);
    }
}
