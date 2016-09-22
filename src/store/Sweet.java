package store;

public class Sweet {

    protected Category category;
    protected Manufacturer manufacturer;
    protected String name;

    protected double price;
    protected int quantity;

    protected int kgInStock;

    public Sweet() {
    }

    public Sweet(int quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
