package lessonsJD.lesson3.cafe.object.menu_items;

import lessonsJD.lesson3.cafe.object.AbstractIngredient;
import lessonsJD.lesson3.cafe.object.AbstractMenuItem;
import lessonsJD.lesson3.cafe.object.ingredients.Coffee;
import lessonsJD.lesson3.cafe.object.ingredients.Milk;
import lessonsJD.lesson3.cafe.object.ingredients.Sugar;
import lessonsJD.lesson3.cafe.object.ingredients.Water;
import lessonsJD.lesson3.cafe.object.optional.ISugar;

public class Cappuccino extends AbstractMenuItem implements ISugar {

    private Water water;
    private Coffee coffee;
    private Milk milk;
    private Sugar sugar;

    public Cappuccino() {
        setPrice(7);
        water = new Water(this, 1);
        coffee = new Coffee(this, 1);
        milk = new Milk(this, 2);
    }

    public void addSugar(int quantity) {
        sugar = new Sugar(this, quantity);
    }

    @Override
    public void setAllIngredients() {
        setAllIngredients(new AbstractIngredient[] {water, coffee, milk, sugar});
    }
}
