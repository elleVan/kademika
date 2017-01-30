package lessonsJD.lesson9;

public class GoodAtm implements Atm {

    private int balance = 1000;

    @Override
    public void withdrawMoney(long accountId, int amount) {

        if (allowWithdrawal(accountId, amount)) {
            updateBalance(accountId, amount);
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
