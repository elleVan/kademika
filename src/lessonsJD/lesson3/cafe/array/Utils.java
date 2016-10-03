package lessonsJD.lesson3.cafe.array;

public class Utils {

    public static final int FAIL = -1;

    public String printIngredients(Ingredient[] ingredients) {

        String result = "";
        for (Ingredient ingredient : ingredients) {
            if (ingredient != null && !result.contains(ingredient.getName())) {
                result += ingredient.getName() + ", ";
            }
        }
        if (result.length() > 0) {
            result = result.substring(0, result.length() - 2);
        }

        return result;
    }

    public Ingredient[] addIngredient(Ingredient ingredient, Ingredient[] ingredients) {

        if (ingredient != null) {
            int idx = findEmptyIngredient(ingredients);
            if (idx == FAIL) {
                idx = ingredients.length;
                ingredients = extendIngredients(ingredients);
            }
            ingredients[idx] = ingredient;
        }
        return ingredients;
    }

    public int findEmptyIngredient(Ingredient[] ingredients) {
        for (int i = 0; i < ingredients.length; i++) {
            if (ingredients[i] == null) {
                return i;
            }
        }
        return FAIL;
    }

    public Ingredient[] extendIngredients(Ingredient[] ingredients) {
        int length = ingredients.length;
        Ingredient[] newArray = new Ingredient[length + length / 4 + 1];
        System.arraycopy(ingredients, 0, newArray, 0, length);
        return newArray;
    }

    public int findIngredient(String name, Ingredient[] ingredients) {

        for (int i = 0; i < ingredients.length; i++) {
            if (ingredients[i] != null && ingredients[i].getName().equals(name)) {
                return i;
            }
        }
        return FAIL;
    }

    public double calculateFinalPrice(MenuItem item) {
        double finalPrice = item.getStartPrice();
        for (Ingredient ingredient : item.getIngredients()) {
            if (ingredient != null) {
                finalPrice += ingredient.getIncreasePerKilo() / 1000 * ingredient.getWeightGrams();
            }
        }
        return finalPrice;
    }
}
