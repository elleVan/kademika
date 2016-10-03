package lessonsJD.lesson3.cafe.object.ingredients;

import lessonsJD.lesson3.cafe.object.AbstractIngredient;
import lessonsJD.lesson3.cafe.object.AbstractMenuItem;

public class GreenTeaLeaf extends AbstractIngredient {

    public GreenTeaLeaf(AbstractMenuItem item, int quantity) {
        super(item, quantity);
        setIncrease(1.5 * quantity);
        item.increasePrice(getIncrease());
    }
}
