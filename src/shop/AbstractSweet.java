package shop;

public abstract class AbstractSweet {

    private final String name = getClass().getSimpleName();

    private double price;
    private int quantity;

    private int kgInStock;

    public AbstractSweet(int quantity) {
        this.setQuantity(quantity);
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getKgInStock() {
        return kgInStock;
    }

    public void setKgInStock(int kgInStock) {
        this.kgInStock = kgInStock;
    }

    public abstract Category getCategory();
}
