package lessonsJD.lesson3.cafe.array;

public class MenuItem {

    private String name;
    private double startPrice;

    private Ingredient[] ingredients = new Ingredient[5];

    public MenuItem(String name, double price, Ingredient[] ingredients) {
        this.name = name;
        this.startPrice = price;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }

    public Ingredient[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredient[] ingredients) {
        this.ingredients = ingredients;
    }
}
