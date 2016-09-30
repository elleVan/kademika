package shop;

import java.util.Arrays;

public class Shop {

    public static final int FAIL = -1;

    public static final int NAME = 0;
    public static final int PRICE = 1;
    public static final int IN_STOCK = 2;

    private Base base = new Base();
    private int today = 8;

    private String[][] sweets = base.getSweets();
    private Customer[] customers = base.getCustomers();
    private Transaction[][] transactions = base.getTransactions();

    public Shop() {

        transactions[8][0].setCustomer(customers[0]);
        Sweet[] sweets1 = new Sweet[] {
                newSweet("Amour", 5),
                newSweet("MAK", 3)
        };
        transactions[8][0].setSweets(sweets1);

        transactions[8][1].setCustomer(customers[2]);
        Sweet[] sweets2 = new Sweet[] {
                newSweet("Konti", 4)
        };
        transactions[8][1].setSweets(sweets2);

    }

    public void run() {
        newTransaction(customers[1], new Sweet[] {
                newSweet("Konti", 20)
        });
    }

    public void printBase() {
        System.out.println("=========== CATALOG ============");
        Catalog catalog = new Catalog();
        catalog.initCatalog(this);
    }

    public void printPrices() {
        System.out.println("============ PRICES ============");
        for (String[] sweet : sweets) {
            if (sweet != null) {
                System.out.println(sweet[NAME] + " - " + sweet[PRICE]);
            }
        }
        System.out.println();
    }

    public void printStock() {
        System.out.println("============ STOCK =============");
        for (String[] sweet : sweets) {
            if (sweet != null) {
                System.out.println(sweet[NAME] + " - " + sweet[IN_STOCK]);
            }
        }
        System.out.println();
    }

    public void getNumberOfOrdersForTheLastWeek() {

        System.out.println("=== ORDERS FOR THE LAST WEEK ===");
        String result = "";
        for (int i = 6; today - 2 - i >= 0 && i >= 0; i--) {
            result += transactions[today - 2 - i].length + ", ";
        }
        System.out.println(Arrays.toString(result.substring(0, result.length() - 1).split(",")) + "\n");
    }

    public void getOrdersForOneDay(int day) {
        String customer;
        int j = 1;

        System.out.println("============= SOLD TODAY =============");
        System.out.printf("%-4s%-12s%-10s%-5s%7s%n", "#", "Customer", "Sweet", "Price", "kg");
        System.out.println("--------------------------------------");

        if (day >= 0 && day < transactions.length) {
            for (Transaction transaction : transactions[day]) {
                if (transaction != null && transaction.getCustomer() != null) {
                    customer = transaction.getCustomer().getName();
                    for (Sweet sweet : transaction.getSweets()) {
                        if (sweet != null) {
                            System.out.printf("%-4d%-12s%-10s%-5d%7d%n", j++, customer, sweet.getName(), sweet.getPrice(),
                                    sweet.getQuantity());
                        }
                    }
                }
            }
        }
        System.out.println("--------------------------------------");
        getTotalForOneDay(day);
        System.out.println();
    }

    public void getTotalForOneDay(int day) {

        int ordersNum = 0;
        double sum = 0;
        int quantity = 0;

        if (day >= 0 && day < transactions.length) {
            for (Transaction transaction : transactions[day]) {
                if (transaction != null) {
                    ordersNum++;
                    if (transaction.getSweets() != null) {
                        for (Sweet sweet : transaction.getSweets()) {
                            if (sweet != null) {
                                sum += sweet.getPrice() * sweet.getQuantity();
                                quantity += sweet.getQuantity();
                            }
                        }
                    }
                }
            }
        }

        System.out.println("Total: " + ordersNum + " transactions  -  $" + sum + "  -  " + quantity + " kg");
    }

    public Sweet newSweet(String name, int quantity) {

        int price = getSweetField(name, PRICE);
        int inStock = getSweetField(name, IN_STOCK);

        if (price != FAIL && inStock != FAIL) {
            return new Sweet(name, quantity, price, inStock);
        }

        return null;
    }

    public Customer newCustomer(String name) {
        int idx = findCustomer(name);
        if (idx == FAIL) {
            idx = findEmptyInCustomers();
            customers[idx] = new Customer(name);
        }
        return customers[idx];
    }

    public boolean newTransaction(Customer customer, Sweet[] sweets) {
        if (sweets != null) {
            for (Sweet sweet : sweets) {
                if (sweet.getQuantity() <= 0) {
                    continue;
                }
                int idx = findSweet(sweet.getName());
                int left = FAIL;
                try {
                    left = Integer.parseInt(this.sweets[idx][IN_STOCK]) - sweet.getQuantity();
                } catch (NumberFormatException e) {

                }

                if (left < 0) {
                    return false;
                }
                this.sweets[idx][IN_STOCK] = Integer.toString(left);
            }
        }

        int idx = findEmptyInTransactionsToday();
        transactions[today][idx] = new Transaction(customer, sweets);
        return true;
    }

    public int getSweetField(String name, int field) {
        int idx = findSweet(name);
        int result = FAIL;
        if (idx != FAIL) {
            try {
                result = Integer.parseInt(sweets[idx][field]);
            } catch (NumberFormatException e) {

            }
        }
        return result;
    }

    public int findEmptyInCustomers() {
        for (int i = 0; i < customers.length; i++) {
            if (customers[i] == null) {
                return i;
            }
        }
        return extendCustomers();
    }

    public int extendCustomers() {
        int length = customers.length;
        Customer[] newArray = new Customer[length + length / 4 + 1];
        System.arraycopy(customers, 0, newArray, 0, length);
        customers = newArray;
        return length;
    }

    public int findEmptyInTransactionsToday() {
        if (transactions[today] == null) {
            transactions[today] = new Transaction[5];
            return 0;
        }

        for (int i = 0; i < transactions[today].length; i++) {
            if (transactions[today][i] == null) {
                return i;
            }
        }

        return extendTransactionsToday();
    }

    public int extendTransactionsToday() {
        int length = 0;
        if (transactions[today] != null) {
            length = transactions[today].length;
        }

        Transaction[] newArray = new Transaction[length + length / 4 + 1];
        System.arraycopy(transactions[today], 0, newArray, 0, length);
        transactions[today] = newArray;
        return length;
    }

    private int findSweet(String name) {
        for (int i = 0; i < sweets.length; i++) {
            if (sweets[i] != null && sweets[i][0].equals(name)) {
                return i;
            }
        }
        return FAIL;
    }

    private int findCustomer(String name) {
        for (int i = 0; i < customers.length; i++) {
            if (customers[i] != null && customers[i].getName().equals(name)) {
                return i;
            }
        }
        return FAIL;
    }

    public Customer[] getCustomers() {
        return customers;
    }

    public Transaction[][] getTransactions() {
        return transactions;
    }

    public int getToday() {
        return today;
    }
}
