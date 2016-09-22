package store;

public class Order {

    private int id;

    private String date;
    private int idCustomer;

    private int[] idsCandies;
    private double sum;

    public Order() {
    }

    public int getId() {
        return id;
    }

    public int[] getIdsCandies() {
        return idsCandies;
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

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }
}
