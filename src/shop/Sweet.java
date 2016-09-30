package shop;

public class Sweet {

    private String name;

    private int quantity;
    private int price;
    private int inStock;

    public Sweet() {
    }

    public Sweet(String name, int quantity, int price, int inStock) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.inStock = inStock;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }
}
