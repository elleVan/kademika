package lessonsJD.lesson3.cafe.object.ingredients;

import lessonsJD.lesson3.cafe.object.AbstractIngredient;
import lessonsJD.lesson3.cafe.object.AbstractMenuItem;

public class Sugar extends AbstractIngredient {

    public Sugar(AbstractMenuItem item, int quantity) {
        super(item, quantity);
        setIncrease(1 * quantity);
        item.increasePrice(getIncrease());
    }
}
