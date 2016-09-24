package store;

public class MAK extends Candy {

    public static final Category category = Category.PRALINE;
    public static final Manufacturer manufacturer = Manufacturer.KONTI;

    public MAK(int quantity) {
        super(quantity);
        price = 14;
        kgInStock = 6;
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
