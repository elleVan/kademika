package shop;

public class Konti extends AbstractCandy {

    public static final Category category = Category.FONDANT;

    public Konti(int quantity) {
        super(quantity);
        setPrice(3.1);
        setKgInStock(15);
    }

    @Override
    public Category getCategory() {
        return category;
    }
}
