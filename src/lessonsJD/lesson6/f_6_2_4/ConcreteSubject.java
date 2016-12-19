package lessonsJD.lesson6.f_6_2_4;

import java.util.ArrayList;
import java.util.List;

public class ConcreteSubject implements Subject {

    private List<Observer> observers;

    public ConcreteSubject() {
        observers = new ArrayList<>();
    }

    public void doTheJob() {
        double d = Math.random();
        if (d < 0.5 || d > 10.0) {
            System.out.println("Job done!");
            notifyObserver();
        } else {
            System.out.println("Job failed!");
        }
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObserver() {
        for (Observer o : observers) {
            o.update();
        }
    }
}