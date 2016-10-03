package lessonsJD.lesson3.cafe.object.ingredients;

import lessonsJD.lesson3.cafe.object.AbstractIngredient;
import lessonsJD.lesson3.cafe.object.AbstractMenuItem;

public class Water extends AbstractIngredient {

    public Water(AbstractMenuItem item, int quantity) {
        super(item, quantity);
        setIncrease(0);
        item.increasePrice(getIncrease());
    }
}
