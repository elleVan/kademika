package shop;

public class Amour extends AbstractCandy {

    public static final Category category = Category.CHOCOLATES;

    public Amour(int quantity) {
        super(quantity);
        setPrice(5.4);
        setKgInStock(10);
    }

    @Override
    public Category getCategory() {
        return category;
    }
}
