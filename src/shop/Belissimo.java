package shop;

public class Belissimo extends AbstractCandy {

    public static final Category category = Category.COMBINED;

    public Belissimo(int quantity) {
        super(quantity);
        this.setPrice(23.1);
        this.setKgInStock(5);
    }

    @Override
    public Category getCategory() {
        return category;
    }
}