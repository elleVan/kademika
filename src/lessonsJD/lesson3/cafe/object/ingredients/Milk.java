package lessonsJD.lesson3.cafe.object.ingredients;

import lessonsJD.lesson3.cafe.object.AbstractIngredient;
import lessonsJD.lesson3.cafe.object.AbstractMenuItem;

public class Milk extends AbstractIngredient {

    public Milk(AbstractMenuItem item, int quantity) {
        super(item, quantity);
        setIncrease(2 * quantity);
        item.increasePrice(getIncrease());
    }
}
