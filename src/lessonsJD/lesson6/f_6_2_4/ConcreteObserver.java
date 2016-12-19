package lessonsJD.lesson6.f_6_2_4;

public class ConcreteObserver implements Observer {

    @Override
    public void update() {
        System.out.println(this.toString() + " notified.");
    }
}
