package shop;

import java.util.*;

public class Shop {

    public static final int FAIL = -1;

    private List<Sweet> sweets;
    private HashSet<Category> categories;
    private List<Customer> customers;
    private List<Transaction> transactions;

    public Shop() {
        initShop(new BaseMemory());
    }

    private void initShop(BaseMemory base) {
        sweets = base.getSweets();
        categories = base.getCategories();
        customers = base.getCustomers();
        transactions = base.getTransactions();
    }

    public void run() {
        carryOutTransaction(chooseCustomer("Alla"), new ArrayList<>(Arrays.asList(
                chooseSweet("MAK", 3),
                chooseSweet("Konti", 5)
        )));

        carryOutTransaction(chooseCustomer("Ben"), new ArrayList<>(Arrays.asList(
                chooseSweet("Amour", 5)
        )));

        carryOutTransaction(customers.get(1), new ArrayList<>(Arrays.asList(
                chooseSweet("Konti", 10)
        )));
    }

    public void printBase() {
        System.out.println("SWEETS");
        for (Sweet sweet : sweets) {
            System.out.println(sweet.getName()  + " - " + sweet.getPrice() + " - " +
                    sweet.getInStock() + " - " + sweet.getCategory());
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
        for (Sweet sweet : sweets) {
            if (sweet != null) {
                System.out.println(sweet.getName() + " - " + sweet.getPrice());
            }
        }
        System.out.println();
    }

    public void printStock() {
        System.out.println("============ STOCK =============");
        for (Sweet sweet : sweets) {
            if (sweet != null) {
                System.out.println(sweet.getName() + " - " + sweet.getInStock());
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

    public Sweet chooseSweet(String name, int quantity) {

        Sweet basic = findSweet(name);
        Sweet result = null;

        if (basic != null) {
            result = new Sweet(basic);
            result.setQuantity(quantity);
        }

        return result;
    }

    public Customer chooseCustomer(String name) {
        int idx = findCustomer(name);
        if (idx == FAIL) {
            customers.add(new Customer(name));
            idx = customers.size() - 1;
        }
        return customers.get(idx);
    }

    public Transaction carryOutTransaction(Customer customer, List<Sweet> boughtSweets) {

        if (customer == null || boughtSweets == null) {
            return null;
        }

        int sum = 0;

        boolean isBoughtSweet = false;
        for (Sweet sweet : boughtSweets) {
            Sweet basic = findSweet(sweet.getName());
            if (sweet.getQuantity() <= 0 || basic == null || basic.getPrice() < 0) {
                continue;
            }

            int left = basic.getInStock() - sweet.getQuantity();
            if (left < 0) {
                return null;
            }
            sweet.setInStock(left);

            int idx = sweets.indexOf(basic);
            sweets.get(idx).setInStock(left);

            sum += basic.getPrice() * sweet.getQuantity();

            isBoughtSweet = true;
        }

        if (!isBoughtSweet) {
            return null;
        }

        Transaction result = new Transaction(customer, boughtSweets);
        result.calculateSum(sum);

        transactions.add(result);

        customer.addTransaction(result);

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

    public void addSweetToTheBase(Sweet sweet) {

        if (sweet != null) {
            Sweet basic = findSweet(sweet.getName());
            if (basic == null) {
                sweets.add(sweet);
            } else {
                int idx = sweets.indexOf(basic);
                sweets.set(idx, sweet);
            }
        }
    }

    public void removeSweetFromTheBase(String name) {
        Sweet sweet = findSweet(name);
        sweets.remove(sweet);
    }

    public Sweet findSweet(String name) {
        for (Sweet sweet : sweets) {
            if (name.equals(sweet.getName())) {
                return sweet;
            }
        }
        return null;
    }

    private int findCustomer(String name) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i) != null && customers.get(i).getName().equals(name)) {
                return i;
            }
        }
        return FAIL;
    }

    public List<Sweet> getSweets() {
        return new ArrayList<>(sweets);
    }

    public HashSet<Category> getCategories() {
        return new HashSet<>(categories);
    }

    public List<Customer> getCustomers() {
        return new ArrayList<>(customers);
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }
}
