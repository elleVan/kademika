package lessonsJD.lesson3.cafe.object.menu_items;

import lessonsJD.lesson3.cafe.object.AbstractIngredient;
import lessonsJD.lesson3.cafe.object.AbstractMenuItem;
import lessonsJD.lesson3.cafe.object.ingredients.*;
import lessonsJD.lesson3.cafe.object.optional.IMilk;
import lessonsJD.lesson3.cafe.object.optional.ISugar;

public class GreenTea extends AbstractMenuItem implements IMilk, ISugar {

    private Water water;
    private GreenTeaLeaf greenTea;
    private Milk milk;
    private Sugar sugar;

    public GreenTea() {
        setPrice(2.5);
        water = new Water(this, 1);
        greenTea = new GreenTeaLeaf(this, 1);
    }

    public void addMilk(int quantity) {
        milk = new Milk(this, quantity);
    }

    public void addSugar(int quantity) {
        sugar = new Sugar(this, quantity);
    }

    @Override
    public void setAllIngredients() {
        setAllIngredients(new AbstractIngredient[] {water, greenTea, milk, sugar});
    }
}
