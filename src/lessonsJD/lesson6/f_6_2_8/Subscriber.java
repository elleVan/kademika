package lessonsJD.lesson6.f_6_2_8;

import java.util.Observable;
import java.util.Observer;

public class Subscriber implements Observer {

    @Override
    public void update(Observable o, Object arg) {

        System.out.println(this.toString() + " got new edition of " + o.getClass().getSimpleName() + " " + arg);
    }
}
