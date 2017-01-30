package lessonsJD.lesson9;

import java.util.HashSet;
import java.util.Set;

public class RaceConditions {

    private long husband = 1234;
    private long wife = 4321;

    private Atm atm;

    public static void main(String[] args) {
        new RaceConditions();
    }

    public RaceConditions() {

        atm = new LegacyAtm();
        Set<Runnable> threads = new HashSet<>();

        for (int i = 0; i < 100; i++) {
            threads.add(createWithdrawalThread(husband, 150));
            threads.add(createWithdrawalThread(wife, 130));
        }

        for (Runnable r : threads) {
            new Thread(r).start();
        }
    }

    private Runnable createWithdrawalThread(long accountId, int amount) {

        return new Runnable() {
            @Override
            public void run() {
                atm.withdrawMoney(accountId, amount);
            }
        };
    }
}
