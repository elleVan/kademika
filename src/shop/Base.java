package shop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Base {

    private List<List<String>> sweets;
    private HashSet<String> categories = new HashSet<>();
    private List<Customer> customers = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();

    public Base() {
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
        sweets.add(new ArrayList<>(Arrays.asList("Amour", "5", "10", "CHOCOLATES")));
        sweets.add(new ArrayList<>(Arrays.asList("Belissimo", "23", "5", "COMBINED")));
        sweets.add(new ArrayList<>(Arrays.asList("Konti", "3", "15", "FONDANT")));
        sweets.add(new ArrayList<>(Arrays.asList("MAK", "14", "6", "PRALINE")));
    }

    public void initCategories() {
        for (List<String> el : sweets) {
            categories.add(el.get(Shop.CATEGORY));
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

    public List<List<String>> getSweets() {
        return new ArrayList<>(sweets);
    }

    public HashSet<String> getCategories() {
        return categories;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
