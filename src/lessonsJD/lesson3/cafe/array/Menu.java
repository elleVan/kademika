package lessonsJD.lesson3.cafe.array;

public class Menu {

    private MenuItem[] menuItems;
    private Ingredient[] ingredients;

    private Utils utils;

    public Menu() {
        utils = new Utils();
        initDemoIngredients();
        initDemoMenuItems();
        run();
    }

    public void run() {
        menuItems[0].setIngredients(utils.addIngredient(getIngredientFromBase("Sugar", 10), menuItems[0].getIngredients()));
    }

    public void initDemoMenuItems() {
        menuItems = new MenuItem[] {
                new MenuItem("Black Coffee", 5, new Ingredient[] {
                        getIngredientFromBase("Water", 30), getIngredientFromBase("Coffee", 8)
                }),
                new MenuItem("White Coffee", 6.4, new Ingredient[] {
                        getIngredientFromBase("Water", 50), getIngredientFromBase("Coffee", 8),
                        getIngredientFromBase("Milk", 50)
                }),
                new MenuItem("Americano", 6, new Ingredient[] {
                        getIngredientFromBase("Water", 150), getIngredientFromBase("Coffee", 8)
                }),
                new MenuItem("Cappuccino", 7, new Ingredient[] {
                        getIngredientFromBase("Water", 30), getIngredientFromBase("Coffee", 10),
                        getIngredientFromBase("Milk", 60)
                }),
                new MenuItem("Mocaccino", 8.2, new Ingredient[] {
                        getIngredientFromBase("Water", 40), getIngredientFromBase("Coffee", 8),
                        getIngredientFromBase("Milk", 40), getIngredientFromBase("Cream", 40),
                        getIngredientFromBase("Chocolate", 15)
                }),
                new MenuItem("Black Tea", 3, new Ingredient[] {
                        getIngredientFromBase("Water", 150), getIngredientFromBase("Black Tea", 5)
                }),
                new MenuItem("Green Tea", 2.5, new Ingredient[] {
                        getIngredientFromBase("Water", 150), getIngredientFromBase("Green Tea", 5)
                }),
                new MenuItem("Earl Grey", 4, new Ingredient[] {
                        getIngredientFromBase("Water", 150), getIngredientFromBase("Earl Grey", 5)
                })
        };
    }

    public void initDemoIngredients() {
        setIngredients(new Ingredient[] {
                new Ingredient("Water", 0, 1),
                new Ingredient("Coffee", 52, 1),
                new Ingredient("Milk", 20, 1),
                new Ingredient("Cream", 23, 1),
                new Ingredient("Chocolate", 49, 1),
                new Ingredient("Sugar", 15, 1),
                new Ingredient("Black Tea", 35, 1),
                new Ingredient("Green Tea", 30, 1),
                new Ingredient("Earl Grey", 45, 1)
        });
    }

    public void printMenu() {
        int i = 1;
        for (MenuItem item : menuItems) {
            if (item != null) {
                System.out.printf("%-4d%-15s%-40s%7.2f%n", i++, item.getName(),
                        utils.printIngredients(item.getIngredients()), utils.calculateFinalPrice(item));
            }
        }
        System.out.println();
        System.out.println("ADDITIONAL:");

        for (Ingredient ingredient : ingredients) {
            if (ingredient != null) {
                System.out.printf("%-4s%-40s%n", "", "+ " + ingredient.getName());
            }
        }

    }

    public Ingredient getIngredientFromBase(String name, int weightGrams) {

        int idx = utils.findIngredient(name, ingredients);
        if (idx != Utils.FAIL) {
            double increase = ingredients[idx].getIncreasePerKilo();
            return new Ingredient(name, increase, weightGrams);
        }
        return null;
    }

    public Ingredient[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredient[] ingredients) {
        this.ingredients = ingredients;
    }
}
