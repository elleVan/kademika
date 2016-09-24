package store;

public class Belissimo extends Candy {

    public static final Category category = Category.COMBINED;
    public static final Manufacturer manufacturer = Manufacturer.KONTI;

    public Belissimo(int quantity) {
        super(quantity);
        price = 23.1;
        kgInStock = 5;
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