package store;

public class Amour extends Candy {

    public Amour(int quantity) {
        super(quantity);
        category = Category.CHOCOLATES;
        manufacturer = Manufacturer.KONTI;
        name = "Amour";
        price = 5.4;
        kgInStock = 10;
    }
}
