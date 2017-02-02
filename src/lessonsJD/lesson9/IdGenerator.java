package lessonsJD.lesson9;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {

    public static void main(String[] args) {
        IdGenerator idGenerator = new IdGenerator();
        CountDownLatch cdl = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        cdl.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(idGenerator.getNextId());
                }
            });

            thread.start();
            cdl.countDown();
        }
    }

    private AtomicLong id;

    public IdGenerator() {
        id = new AtomicLong(0);
    }

    public long getNextId() {
        return id.incrementAndGet();
    }
}
