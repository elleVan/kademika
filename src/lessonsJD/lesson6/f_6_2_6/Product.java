package lessonsJD.lesson6.f_6_2_6;

import java.util.ArrayList;
import java.util.List;

public abstract class Product {

    private List<Subscriber> subscribers;
    private String name;

    public Product(String name) {
        subscribers = new ArrayList<>();
        this.name = name;
    }

    public void newEdition() {

        notifySubscriber();

    }

    public void addSubscriber(Subscriber s) {
        subscribers.add(s);
    }

    public void removeSubscriber(Subscriber s) {
        subscribers.remove(s);
    }

    private void notifySubscriber() {
        for (Subscriber s : subscribers) {
            s.inform(this.getClass().getSimpleName() + " " + name);
        }
    }
}
