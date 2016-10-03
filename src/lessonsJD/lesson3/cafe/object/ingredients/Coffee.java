package lessonsJD.lesson3.cafe.object.ingredients;

import lessonsJD.lesson3.cafe.object.AbstractIngredient;
import lessonsJD.lesson3.cafe.object.AbstractMenuItem;

public class Coffee extends AbstractIngredient {

    public Coffee(AbstractMenuItem item, int quantity) {
        super(item, quantity);
        setIncrease(2.2 * quantity);
        item.increasePrice(getIncrease());
    }
}
