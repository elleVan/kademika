package lessonsJD.lesson9;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SchoolSkatingRink implements SkatingRink {

    private List<Skates> skatesList;
    private List<Skater> skatersWait;

    private Lock lock;

    public SchoolSkatingRink() {
        skatersWait = new ArrayList<>();
        skatesList = new ArrayList<>();

        skatesList.add(new Skates());
        skatesList.add(new Skates());

        lock = new ReentrantLock();
    }

    @Override
    public Skates getSkates(Skater skater) {

        lock.lock();

        if (skatesList.size() == 0) {
            skatersWait.add(skater);
            lock.unlock();

            try {
                synchronized (skater) {
                    System.out.println(skater.getName() + " waits");
                    skater.wait();
                    lock.lock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(skater.getName() + " got skates");

        Skates skates = skatesList.get(0);
        skatersWait.remove(skater);
        skatesList.remove(skates);

        lock.unlock();

        return skates;
    }

    @Override
    public void returnSkates(Skates skates, Skater skater) {

        lock.lock();

        skatesList.add(skates);
        System.out.println(skater.getName() + " returned skates");

        if (skatersWait.size() > 0) {
            synchronized (skatersWait.get(0)) {
                System.out.println(skatersWait.get(0).getName() + " was notified");
                skatersWait.get(0).notify();
            }
        }

        lock.unlock();
    }
}
