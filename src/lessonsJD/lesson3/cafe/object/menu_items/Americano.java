package lessonsJD.lesson3.cafe.object.menu_items;

import lessonsJD.lesson3.cafe.object.AbstractIngredient;
import lessonsJD.lesson3.cafe.object.AbstractMenuItem;
import lessonsJD.lesson3.cafe.object.ingredients.*;
import lessonsJD.lesson3.cafe.object.optional.ICream;
import lessonsJD.lesson3.cafe.object.optional.IMilk;
import lessonsJD.lesson3.cafe.object.optional.ISugar;

public class Americano extends AbstractMenuItem implements IMilk, ICream, ISugar {

    private Water water;
    private Coffee coffee;
    private Milk milk;
    private Cream cream;
    private Sugar sugar;

    public Americano() {
        setPrice(6);
        water = new Water(this, 2);
        coffee = new Coffee(this, 1);
    }

    public void addMilk(int quantity) {
        milk = new Milk(this, quantity);
    }

    public void addCream(int quantity) {
        cream = new Cream(this, quantity);
    }

    public void addSugar(int quantity) {
        sugar = new Sugar(this, quantity);
    }

    @Override
    public void setAllIngredients() {
        setAllIngredients(new AbstractIngredient[] {water, coffee, milk, cream, sugar});
    }
}
