package lessonsJD.lesson9;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BadAtm implements Atm {

    private Lock accountLock;
    private int balance = 1000;

    public BadAtm() {
        accountLock = new ReentrantLock();
    }

    @Override
    public void withdrawMoney(long accountId, int amount) {

        accountLock.lock();

        try {
            if (allowWithdrawal(accountId, amount)) {
                updateBalance(accountId, amount);
            }
        } finally {
            accountLock.unlock();
        }

    }

    private boolean allowWithdrawal(long accountId, int amount) {
        return balance - amount >= 0;
    }

    private void updateBalance(long accountId, int amount) {
        balance -= amount;
        System.out.println("Withdraw " + amount + " from " + accountId + ". Balance: " + balance);
    }
}
