package lessonsJD.lesson6.f_6_2_8;

import java.util.Observable;

public abstract class Product extends Observable {

    private String name;

    public Product(String name) {
        this.name = name;
    }

    public void newEdition() {

        setChanged();
        notifyObservers(name);
    }

    public String getName() {
        return name;
    }
}
