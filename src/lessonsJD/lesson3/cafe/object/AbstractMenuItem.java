package lessonsJD.lesson3.cafe.object;

public abstract class AbstractMenuItem {

    private final String name = getClass().getSimpleName();

    private double price;

    private AbstractIngredient[] allIngredients = new AbstractIngredient[5];

    public AbstractMenuItem() {

    }

    public void increasePrice(double increase) {
        price += increase;
    }

    public String getIngredients() {

        String result = "";
        setAllIngredients();
        for (AbstractIngredient ingredient : allIngredients) {
            if (ingredient != null) {
                result += ingredient.getClass().getSimpleName() + ", ";
            }
        }
        if (result.length() > 0) {
            result = result.substring(0, result.length() - 2);
        }

        return result;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public AbstractIngredient[] getAllIngredients() {
        return allIngredients;
    }

    public void setAllIngredients() {

    }

    public void setAllIngredients(AbstractIngredient[] allIngredients) {
        this.allIngredients = allIngredients;
    }
}
