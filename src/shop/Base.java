package shop;

import java.util.Arrays;

public class Base {

    public static final int FAIL = -1;

    private AbstractSweet[] sweets = new AbstractSweet[100];
    private Order[][] orders = new Order[30][];
    private Customer[] customers = new Customer[10];

    public Base() {
        initDemoBase();
    }


    public void initDemoBase() {
        initDemoSweets();
        initDemoCustomers();
        initDemoOrders();
    }

    public void initDemoSweets() {
        sweets[0] = new Amour(1);
        sweets[1] = new Belissimo(1);
        sweets[2] = new Konti(1);
        sweets[3] = new MAK(10);
    }

    private void initDemoCustomers() {
        customers[0] = new Customer("Nick");
        customers[1] = new Customer("Ben");
        customers[2] = new Customer("Anna");
        customers[3] = new Customer("John");

    }

    private void initDemoOrders() {
        orders[0] = new Order[3];
        orders[1] = new Order[2];
        orders[2] = new Order[0];
        orders[3] = new Order[5];
        orders[4] = new Order[1];
        orders[5] = new Order[0];
        orders[6] = new Order[4];
        orders[7] = new Order[3];
        orders[8] = new Order[2];

        for (int i = 0; i < orders.length; i++) {
            for (int j = 0; orders[i] != null && j < orders[i].length; j++) {
                orders[i][j] = new Order();
            }
        }

        orders[8][0].setCustomer(customers[0]);
        AbstractSweet[] sweets1 = new AbstractSweet[2];
        sweets1[0] = sweets[0];
        sweets1[1] = sweets[3];
        orders[8][0].setSweets(sweets1);

        orders[8][1].setCustomer(customers[2]);
        AbstractSweet[] sweets2 = new AbstractSweet[1];
        sweets2[0] = sweets[2];
        orders[8][1].setSweets(sweets2);
    }

    public void printBase() {
        System.out.println("=========== CATALOG ============");
        Catalog catalog = new Catalog();
        catalog.initCatalog(this);
    }

    public void getPrices() {
        System.out.println("============ PRICES ============");
        for (AbstractSweet sweet : sweets) {
            if (sweet != null) {
                System.out.println(sweet.getName() + " - " + sweet.getPrice());
            }
        }
        System.out.println();
    }

    public void getStock() {
        System.out.println("============ STOCK =============");
        for (AbstractSweet sweet : sweets) {
            if (sweet != null) {
                System.out.println(sweet.getName() + " - " + sweet.getKgInStock());
            }
        }
        System.out.println();
    }

    public void getNumberOfOrdersForTheLastWeek() {

        System.out.println("=== ORDERS FOR THE LAST WEEK ===");
        String result = "";
        int tomorrow = findEmptyInOrders();
        for (int i = 6; tomorrow - 1 - i >= 0 && i >= 0; i--) {
            result += orders[tomorrow - 1 - i].length + ", ";
        }
        System.out.println(Arrays.toString(result.substring(0, result.length() - 1).split(",")) + "\n");
    }

    public void getOrdersOfToday() {
        int today = findEmptyInOrders() - 1;
        if (findEmptyInOrders() - 1 < 0) {
            today = 0;
        }
        getOrdersForOneDay(today);
    }

    public void getOrdersForOneDay(int day) {
        String customer;
        int j = 1;

        System.out.println("============= SOLD TODAY =============");
        System.out.printf("%-4s%-12s%-10s%-5s%7s%n", "#", "Customer", "AbstractSweet", "Price", "kg");
        System.out.println("--------------------------------------");

        if (day >= 0 && day < orders.length) {
            for (Order order : orders[day]) {
                if (order != null) {
                    customer = order.getCustomer().getName();
                    for (AbstractSweet sweet : order.getSweets()) {
                        System.out.printf("%-4d%-12s%-10s%-5.2f%7d%n", j++, customer, sweet.getName(), sweet.getPrice(),
                                sweet.getQuantity());
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

        if (day >= 0 && day < orders.length) {
            for (Order order : orders[day]) {
                if (order != null) {
                    ordersNum++;
                    for (AbstractSweet sweet : order.getSweets()) {
                        sum += sweet.getPrice() * sweet.getQuantity();
                        quantity += sweet.getQuantity();
                    }
                }
            }
        }

        System.out.println("Total: " + ordersNum + " orders  -  $" + sum + "  -  " + quantity + " kg");
    }

    public int findEmptyInOrders() {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) {
                return i;
            }
        }
        return FAIL;
    }

    public AbstractSweet[] getSweets() {
        return sweets;
    }

    public Order[][] getOrders() {
        return orders;
    }

    public Customer[] getCustomers() {
        return customers;
    }
}
