package store;

public class MAK extends Candy {

    public MAK(int quantity) {
        super(quantity);
        category = Category.PRALINE;
        manufacturer = Manufacturer.KONTI;
        name = "MAK";
        price = 14;
        kgInStock = 6;
    }
}
