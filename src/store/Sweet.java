package store;

public class Sweet {

    public static final Category category = Category.NONE;
    public static final Manufacturer manufacturer = Manufacturer.NONE;
    public final String name = getClass().getSimpleName();

    protected double price;
    protected int quantity;

    protected int kgInStock;

    public Sweet(int quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
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
}
