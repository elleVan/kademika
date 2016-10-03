package lessonsJD.lesson3.cafe.object.ingredients;

import lessonsJD.lesson3.cafe.object.AbstractIngredient;
import lessonsJD.lesson3.cafe.object.AbstractMenuItem;

public class Cream extends AbstractIngredient {

    public Cream(AbstractMenuItem item, int quantity) {
        super(item, quantity);
        setIncrease(3.2 * quantity);
        item.increasePrice(getIncrease());
    }
}
