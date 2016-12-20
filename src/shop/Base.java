package shop;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Base {

    private String[][] sweets;
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

        sweets = new String[][] {
                //name, price, in stock, category
                {"Amour", "5", "10", "CHOCOLATES"},
                {"Belissimo", "23", "5", "COMBINED"},
                {"Konti", "3", "15", "FONDANT"},
                {"MAK", "14", "6", "PRALINE"}
        };
    }

    private void initCategories() {
        for (String[] el : sweets) {
            categories.add(el[Shop.CATEGORY]);
        }
    }

    private void initDemoCustomers() {
        customers.add(new Customer("Nick"));
        customers.add(new Customer("Ben"));
        customers.add(new Customer("Anna"));
        customers.add(new Customer("John"));

    }

    private void initDemoOrders() {

    }

    public String[][] getSweets() {
        return sweets;
    }

    public HashSet<String> getCategories() {
        return new HashSet<>(categories);
    }

    public List<Customer> getCustomers() {
        return new ArrayList<>(customers);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
