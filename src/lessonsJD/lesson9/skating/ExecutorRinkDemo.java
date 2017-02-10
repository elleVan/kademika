package lessonsJD.lesson9.skating;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorRinkDemo {

    public static void main(String[] args) {

        final AnotherSkatingRink skatingRink = new AnotherSkatingRink();
        ExecutorService executor = Executors.newFixedThreadPool(2);

        final Random random = new Random();

        try {
            for (int i = 0; i < 10; i++) {

                final Skater skater = new Skater("Skater " + i);

                executor.submit(new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        Skates skates = skatingRink.getSkates(skater);
                        sleep(random.nextInt(2000));
                        skatingRink.returnSkates(skates, skater);
                        return skates;
                    }
                });

                sleep(random.nextInt(1000));
            }
        } finally {
            executor.shutdown();
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
