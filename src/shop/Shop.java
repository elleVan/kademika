package shop;

import java.util.*;

public class Shop {

    public static final int FAIL = -1;

    public static final int NAME = 0;
    public static final int PRICE = 1;
    public static final int IN_STOCK = 2;
    public static final int CATEGORY = 3;

    private List<List<String>> sweets;
    private HashSet<String> categories;
    private List<Customer> customers;
    private List<Transaction> transactions;

    public Shop() {
        initShop(new Base());
    }

    public Shop(Base base) {
        initShop(base);
    }

    private void initShop(Base base) {
        sweets = base.getSweets();
        categories = base.getCategories();
        customers = base.getCustomers();
        transactions = base.getTransactions();
    }

    public void run() {
        newTransaction(newCustomer("Alla"), new Sweet[] {
                newSweet("MAK", 3),
                newSweet("Konti", 5)
        });
        newTransaction(newCustomer("Ben"), new Sweet[] {
                newSweet("Amour", 5),
        });

        newTransaction(customers.get(1), new Sweet[] {
                newSweet("Konti", 10)
        });
    }

    public void printBase() {
        System.out.println("SWEETS");
        for (List<String> sweet : sweets) {
            System.out.println(Arrays.toString(sweet.toArray()));
        }
        System.out.println();
        System.out.println("CUSTOMERS");
        for (Customer customer : customers) {
            if (customer != null) {
                System.out.println(customer.getName().toUpperCase());
                if (customer.getTransactions() != null) {
                    for (Transaction transaction : customer.getTransactions()) {
                        if (transaction != null) {
                            for (Sweet sweet : transaction.getSweets()) {
                                System.out.println(sweet.getName() + " - " + sweet.getQuantity() + " - " +
                                        sweet.getPrice() + " - " + sweet.getInStock());
                            }
                            System.out.println();
                        }
                    }
                }
            }
        }
    }

    public void printCatalog() {
        System.out.println("=========== CATALOG ============");
        Catalog catalog = new Catalog();
        catalog.printCatalog(this);
    }

    public void printPrices() {
        System.out.println("============ PRICES ============");
        for (List<String> sweet : sweets) {
            if (sweet != null) {
                System.out.println(sweet.get(NAME) + " - " + sweet.get(PRICE));
            }
        }
        System.out.println();
    }

    public void printStock() {
        System.out.println("============ STOCK =============");
        for (List<String> sweet : sweets) {
            if (sweet != null) {
                System.out.println(sweet.get(NAME) + " - " + sweet.get(IN_STOCK));
            }
        }
        System.out.println();
    }

    public void getOrdersForOneDay(Date date) {
        String customer;
        int j = 1;

        System.out.println("============= SOLD TODAY =============");
        System.out.printf("%-4s%-12s%-10s%-5s%7s%n", "#", "Customer", "Sweet", "Price", "kg");
        System.out.println("--------------------------------------");

        for (Transaction transaction : transactions) {
            if (transaction != null && isOneDay(date, transaction.getDate())) {
                customer = transaction.getCustomer().getName();
                for (Sweet sweet : transaction.getSweets()) {
                    if (sweet != null) {
                        System.out.printf("%-4d%-12s%-10s%-5d%7d%n", j++, customer, sweet.getName(), sweet.getPrice(),
                                sweet.getQuantity());
                    }
                }
            }
        }

        System.out.println("--------------------------------------");
        getTotalForOneDay(date);
        System.out.println();
    }

    public void getTotalForOneDay(Date date) {

        int ordersNum = 0;
        double sum = 0;
        int quantity = 0;

        for (Transaction transaction : transactions) {
            if (transaction != null && isOneDay(date, transaction.getDate())) {
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

        System.out.println("Total: " + ordersNum + " transactions  -  $" + sum + "  -  " + quantity + " kg");
    }

    public Sweet newSweet(String name, int quantity) {

        int price = getSweetsNumericField(name, PRICE);
        int inStock = getSweetsNumericField(name, IN_STOCK);
        String category = getSweetsStringField(name, CATEGORY);

        if (price != FAIL && inStock != FAIL && quantity >= 0 && quantity <= inStock) {
            return new Sweet(name, quantity, price, inStock, category);
        }

        return null;
    }

    public Customer newCustomer(String name) {
        int idx = findCustomer(name);
        if (idx == FAIL) {
            customers.add(new Customer(name));
            idx = customers.size() - 1;
        }
        return customers.get(idx);
    }

    public Transaction newTransaction(Customer customer, Sweet[] boughtSweets) {
        if (boughtSweets != null) {
            for (Sweet sweet : boughtSweets) {
                int idx = findSweet(sweet.getName());
                if (sweet.getQuantity() <= 0 || idx == FAIL) {
                    continue;
                }

                int left = FAIL;
                try {
                    left = Integer.parseInt(sweets.get(idx).get(IN_STOCK)) - sweet.getQuantity();
                } catch (NumberFormatException e) {

                }

                if (left < 0) {
                    return null;
                }
                sweets.get(idx).set(IN_STOCK, Integer.toString(left));
            }
        }

        Transaction result = new Transaction(customer, boughtSweets);
        transactions.add(result);

        customer.addTransaction(transactions.get(transactions.size() - 1));

        if (findCustomer(customer.getName()) == FAIL) {
            customers.add(customer);
        }

        return result;
    }

    public boolean isOneDay(Date date1, Date date2) {

        Calendar calendar1 = new GregorianCalendar();
        Calendar calendar2 = new GregorianCalendar();

        calendar1.setTime(date1);
        calendar2.setTime(date2);

        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
                && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)
                && calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH);
    }

    public void addSweetToTheBase(String name, int price, int inStock, String category) {

        List<String> row = new ArrayList<>(Arrays.asList(name, Integer.toString(price), Integer.toString(inStock), category));
        int idx = findSweet(name);
        if (idx == FAIL) {
            sweets.add(row);
        } else {
            sweets.set(idx, row);
        }
    }

    public void removeSweetFromTheBase(String name) {
        int idx = findSweet(name);
        sweets.remove(idx);
    }

    public int getSweetsNumericField(String name, int field) {
        int idx = findSweet(name);
        int result = FAIL;
        if (idx != FAIL && field < sweets.get(idx).size()) {
            try {
                result = Integer.parseInt(sweets.get(idx).get(field));
            } catch (NumberFormatException e) {

            }
        }
        return result;
    }

    public String getSweetsStringField(String name, int field) {
        int idx = findSweet(name);
        String result = null;
        if (idx != FAIL && field < sweets.get(idx).size()) {
            result = sweets.get(idx).get(field);
        }
        return result;
    }

    public int findSweet(String name) {
        for (int i = 0; i < sweets.size(); i++) {
            if (sweets.get(i) != null && sweets.get(i).get(NAME).equals(name)) {
                return i;
            }
        }
        return FAIL;
    }

    private int findCustomer(String name) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i) != null && customers.get(i).getName().equals(name)) {
                return i;
            }
        }
        return FAIL;
    }

    public List<List<String>> getSweets() {
        return new ArrayList<>(sweets);
    }

    public HashSet<String> getCategories() {
        return new HashSet<>(categories);
    }

    public List<Customer> getCustomers() {
        return new ArrayList<>(customers);
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }
}
