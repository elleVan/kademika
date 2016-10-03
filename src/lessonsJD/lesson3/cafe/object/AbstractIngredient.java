package lessonsJD.lesson3.cafe.object;

public abstract class AbstractIngredient {

    private String name;
    private int quantity;
    private double increase;

    public AbstractIngredient(AbstractMenuItem item, int quantity) {
        setQuantity(quantity);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getIncrease() {
        return increase;
    }

    public void setIncrease(double increase) {
        this.increase = increase;
    }
}
