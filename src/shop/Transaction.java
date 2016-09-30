package shop;

public class Transaction {

    private String date;
    private Customer customer;

    private Sweet[] sweets;

    public Transaction() {
    }

    public Transaction(Customer customer, Sweet[] sweets) {
        this.customer = customer;
        this.sweets = sweets;
    }

    public Sweet[] getSweets() {
        return sweets;
    }

    public void setSweets(Sweet[] sweets) {
        this.sweets = sweets;
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
