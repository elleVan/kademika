package shop;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String name;
    private String surname;

    private String phone;
    private String email;
    private String address;

    private List<Object> transactions = new ArrayList<>();

    public Customer() {
    }

    public Customer(String name) {
        this.name = name;
    }

    public List<Object> getTransactions() {
        return new ArrayList<>(transactions);
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
