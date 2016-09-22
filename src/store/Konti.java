package store;

public class Konti extends Candy {

    public Konti(int quantity) {
        super(quantity);
        category = Category.FONDANT;
        manufacturer = Manufacturer.KONTI;
        name = "Konti";
        price = 3.1;
        kgInStock = 15;
    }
}
