package shop;

public class MAK extends AbstractCandy {

    public static final Category category = Category.PRALINE;

    public MAK(int quantity) {
        super(quantity);
        setPrice(14);
        setKgInStock(6);
    }

    @Override
    public Category getCategory() {
        return category;
    }
}
