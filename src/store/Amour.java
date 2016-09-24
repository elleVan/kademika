package store;

public class Amour extends Candy {

    public static final Category category = Category.CHOCOLATES;
    public static final Manufacturer manufacturer = Manufacturer.KONTI;

    public Amour(int quantity) {
        super(quantity);
        price = 5.4;
        kgInStock = 10;
    }

    @Override
    public Category getCategory() {
        return category;
    }

    @Override
    public Manufacturer getManufacturer() {
        return manufacturer;
    }
}
