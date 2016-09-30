package shop;

public class Order {

    private String date;
    private Customer customer;

    private AbstractSweet[] sweets;
    private double sum;

    public Order() {
    }

    public Order(Customer customer, AbstractSweet[] sweets) {
        this.customer = customer;
        this.sweets = sweets;
    }

    public AbstractSweet[] getSweets() {
        return sweets;
    }

    public void setSweets(AbstractSweet[] sweets) {
        this.sweets = sweets;
    }

    public double getSum() {
        return sum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
