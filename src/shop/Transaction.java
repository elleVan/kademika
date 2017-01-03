package shop;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Transaction {

    private Date date;
    private Customer customer;

    private List<Sweet> sweets;

    private int sum;

    public Transaction() {
    }

    public Transaction(Customer customer, List<Sweet> sweets) {
        this.customer = customer;
        this.sweets = sweets;
        date = new Date();
    }

    public void calculateSum(int sumBefore) {
        if (sumBefore >= 500 && sumBefore < 1000) {
            setSum(sumBefore - sumBefore / 100 * 5);
        } else if (sumBefore >= 1000) {
            setSum(sumBefore - sumBefore / 10);
        } else {
            setSum(sumBefore);
        }
    }

    public List<Sweet> getSweets() {
        return new ArrayList<>(sweets);
    }

    public void setSweets(List<Sweet> sweets) {
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

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
