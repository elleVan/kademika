package lessonsJD.lesson3.cafe.object.menu_items;

import lessonsJD.lesson3.cafe.object.AbstractIngredient;
import lessonsJD.lesson3.cafe.object.AbstractMenuItem;
import lessonsJD.lesson3.cafe.object.ingredients.*;
import lessonsJD.lesson3.cafe.object.optional.ISugar;

public class EarlGrey extends AbstractMenuItem implements ISugar {

    private Water water;
    private EarlGreyLeaf earlGrey;
    private Sugar sugar;

    public EarlGrey() {
        setPrice(4);
        water = new Water(this, 1);
        earlGrey = new EarlGreyLeaf(this, 1);
    }

    public void addSugar(int quantity) {
        sugar = new Sugar(this, quantity);
    }

    @Override
    public void setAllIngredients() {
        setAllIngredients(new AbstractIngredient[] {water, earlGrey, sugar});
    }
}
