package shop;

public class Base {

    private String[][] sweets;
    private Customer[] customers = new Customer[10];
    private Transaction[][] transactions = new Transaction[30][];

    public Base() {
        initDemoBase();
    }

    public void initDemoBase() {
        initDemoSweets();
        initDemoCustomers();
        initDemoOrders();
    }

    public void initDemoSweets() {

        sweets = new String[][] {
                //name, price, in stock
                {"Amour", "5", "10"},
                {"Belissimo", "23", "5"},
                {"Konti", "3", "15"},
                {"MAK", "14", "6"}
        };


    }

    private void initDemoCustomers() {
        customers[0] = new Customer("Nick");
        customers[1] = new Customer("Ben");
        customers[2] = new Customer("Anna");
        customers[3] = new Customer("John");

    }

    private void initDemoOrders() {
        transactions[0] = new Transaction[3];
        transactions[1] = new Transaction[2];
        transactions[2] = new Transaction[0];
        transactions[3] = new Transaction[5];
        transactions[4] = new Transaction[1];
        transactions[5] = new Transaction[0];
        transactions[6] = new Transaction[4];
        transactions[7] = new Transaction[3];
        transactions[8] = new Transaction[2];

        for (int i = 0; i < transactions.length; i++) {
            for (int j = 0; transactions[i] != null && j < transactions[i].length; j++) {
                transactions[i][j] = new Transaction();
            }
        }

    }

    public String[][] getSweets() {
        return sweets;
    }

    public Customer[] getCustomers() {
        return customers;
    }

    public Transaction[][] getTransactions() {
        return transactions;
    }
}
