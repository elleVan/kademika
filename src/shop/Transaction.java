package shop;

import java.util.Date;

public class Transaction {

    private Date date;
    private Customer customer;

    private Sweet[] sweets;

    public Transaction() {
    }

    public Transaction(Customer customer, Sweet[] sweets) {
        this.customer = customer;
        this.sweets = sweets;
        date = new Date();
    }

    public Sweet[] getSweets() {
        return sweets;
    }

    public void setSweets(Sweet[] sweets) {
        this.sweets = sweets;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
