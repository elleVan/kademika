package shop;

public class Customer {

    private String name;
    private String surname;

    private String phone;
    private String email;
    private String address;

    private Transaction[] transactions = new Transaction[5];

    public Customer() {
    }

    public Customer(String name) {
        this.name = name;
    }

    public Transaction[] getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions[findEmptyInTransactions()] = transaction;
    }

    public int findEmptyInTransactions() {
        if (transactions == null) {
            transactions = new Transaction[5];
            return 0;
        }

        for (int i = 0; i < transactions.length; i++) {
            if (transactions[i] == null) {
                return i;
            }
        }

        return extendTransactionsToday();
    }

    public int extendTransactionsToday() {
        int length = 0;
        if (transactions != null) {
            length = transactions.length;
        }

        Transaction[] newArray = new Transaction[length + length / 4 + 1];
        System.arraycopy(transactions, 0, newArray, 0, length);
        transactions = newArray;
        return length;
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
