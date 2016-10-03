package lessonsJD.lesson3.cafe.object.ingredients;

import lessonsJD.lesson3.cafe.object.AbstractIngredient;
import lessonsJD.lesson3.cafe.object.AbstractMenuItem;

public class EarlGreyLeaf extends AbstractIngredient {

    public EarlGreyLeaf(AbstractMenuItem item, int quantity) {
        super(item, quantity);
        setIncrease(2.5 * quantity);
        item.increasePrice(getIncrease());
    }
}
