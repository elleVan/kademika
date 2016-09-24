package store;

public class Konti extends Candy {

    public static final Category category = Category.FONDANT;
    public static final Manufacturer manufacturer = Manufacturer.KONTI;

    public Konti(int quantity) {
        super(quantity);
        price = 3.1;
        kgInStock = 15;
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
