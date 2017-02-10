package shop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class BaseMemory implements Base {

    private List<Sweet> sweets;
    private HashSet<Category> categories = new HashSet<>();
    private List<Customer> customers = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();

    public BaseMemory() {
        initDemoBase();
    }

    public void initDemoBase() {
        initDemoSweets();
        initCategories();
        initDemoCustomers();
        initDemoOrders();
    }

    private void initDemoSweets() {

        sweets = new ArrayList<>();
        sweets.add(new Sweet("Amour", 0, 5, 10, Category.CHOCOLATES));
        sweets.add(new Sweet("Belissimo", 0, 23, 5, Category.COMBINED));
        sweets.add(new Sweet("Konti", 0, 3, 15, Category.FONDANT));
        sweets.add(new Sweet("MAK", 0, 14, 6, Category.PRALINE));
    }

    public void initCategories() {
        for (Sweet sweet : sweets) {
            categories.add(sweet.getCategory());
        }
    }

    public void initDemoCustomers() {
        customers.add(new Customer("Nick"));
        customers.add(new Customer("Ben"));
        customers.add(new Customer("Anna"));
        customers.add(new Customer("John"));

    }

    public void initDemoOrders() {

    }

    public List<Sweet> getSweets() {
        return new ArrayList<>(sweets);
    }

    public HashSet<Category> getCategories() {
        return categories;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
