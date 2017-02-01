package lessonsJD.lesson9.skating;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SchoolSkatingRink implements SkatingRink {

    private List<Skates> skatesList;

    private Lock lock;

    public SchoolSkatingRink() {
        skatesList = new ArrayList<>();

        skatesList.add(new Skates());
        skatesList.add(new Skates());

        lock = new ReentrantLock();
    }

    @Override
    public Skates getSkates(Skater skater) {

        if (skatesList.isEmpty()) {

            try {
                synchronized (skatesList) {
                    System.out.println(skater.getName() + " waits");
                    skatesList.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        lock.lock();

        Skates skates = skatesList.remove(0);

        lock.unlock();

        System.out.println(skater.getName() + " got skates");

        return skates;
    }

    @Override
    public void returnSkates(Skates skates, Skater skater) {

        skatesList.add(skates);
        System.out.println(skater.getName() + " returned skates");

        synchronized (skatesList) {
            skatesList.notify();
        }

    }
}
