package store;

public class Candy {

    private int id;
    private Manufacturer manufacturer;
    private String name;

    private double price;

    private int kgInStock;

    public Candy() {
    }

    public int getId() {
        return id;
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

    public int getKgInStock() {
        return kgInStock;
    }

    public void setKgInStock(int kgInStock) {
        this.kgInStock = kgInStock;
    }
}
