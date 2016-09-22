package store;

public class Customer {

    private int id;

    private String name;
    private String surname;

    private String phone;
    private String email;
    private String address;

    private int[] idsOrders;

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public int[] getIdsOrders() {
        return idsOrders;
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
