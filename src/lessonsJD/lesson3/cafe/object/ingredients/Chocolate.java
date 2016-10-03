package lessonsJD.lesson3.cafe.object.ingredients;

import lessonsJD.lesson3.cafe.object.AbstractIngredient;
import lessonsJD.lesson3.cafe.object.AbstractMenuItem;

public class Chocolate extends AbstractIngredient {

    public Chocolate(AbstractMenuItem item, int quantity) {
        super(item, quantity);
        setIncrease(3.5 * quantity);
        item.increasePrice(getIncrease());
    }
}
