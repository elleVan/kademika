package lessonsJD.lesson3.cafe.object.menu_items;

import lessonsJD.lesson3.cafe.object.AbstractIngredient;
import lessonsJD.lesson3.cafe.object.AbstractMenuItem;
import lessonsJD.lesson3.cafe.object.ingredients.BlackTeaLeaf;
import lessonsJD.lesson3.cafe.object.ingredients.Milk;
import lessonsJD.lesson3.cafe.object.ingredients.Sugar;
import lessonsJD.lesson3.cafe.object.ingredients.Water;
import lessonsJD.lesson3.cafe.object.optional.IMilk;
import lessonsJD.lesson3.cafe.object.optional.ISugar;

public class BlackTea extends AbstractMenuItem implements IMilk, ISugar {
    private Water water;
    private BlackTeaLeaf blackTea;
    private Milk milk;
    private Sugar sugar;

    public BlackTea() {
        setPrice(3);
        water = new Water(this, 1);
        blackTea = new BlackTeaLeaf(this, 1);
    }

    public void addMilk(int quantity) {
        milk = new Milk(this, quantity);
    }

    public void addSugar(int quantity) {
        sugar = new Sugar(this, quantity);
    }

    @Override
    public void setAllIngredients() {
        setAllIngredients(new AbstractIngredient[] {water, blackTea, milk, sugar});
    }
}
