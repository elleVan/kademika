package lessonsJD.lesson9.skating;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RinkDemo {

    public static void main(String[] args) {

        final AnotherSkatingRink skatingRink = new AnotherSkatingRink();
        final Lock lock = new ReentrantLock();
        final Random random = new Random();

        for (int i = 0; i < 10; i++) {

            final Skater skater = new Skater("Skater " + i);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        lock.lock();
                        if (skatingRink.isAvailableSkates()) {
                            Skates skates = skatingRink.getSkates(skater);
                            lock.unlock();

                            sleep(random.nextInt(2000));
                            skatingRink.returnSkates(skates, skater);
                            break;
                        } else {
                            lock.unlock();
                        }
                    }
                }
            }).start();

            sleep(random.nextInt(1000));
        }
    }

    private static void sleep(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
