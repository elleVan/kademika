package store;

public class Belissimo extends Candy {

    public Belissimo(int quantity) {
        super(quantity);
        category = Category.COMBINED;
        manufacturer = Manufacturer.KONTI;
        name = "Belissimo";
        price = 23.1;
        kgInStock = 5;

    }
}