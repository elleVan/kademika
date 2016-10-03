package lessonsJD.lesson3.cafe.object.menu_items;

import lessonsJD.lesson3.cafe.object.AbstractIngredient;
import lessonsJD.lesson3.cafe.object.AbstractMenuItem;
import lessonsJD.lesson3.cafe.object.ingredients.*;
import lessonsJD.lesson3.cafe.object.optional.ISugar;

public class Mocaccino extends AbstractMenuItem implements ISugar {

    private Water water;
    private Coffee coffee;
    private Milk milk;
    private Cream cream;
    private Chocolate chocolate;
    private Sugar sugar;

    public Mocaccino() {
        setPrice(8.2);
        water = new Water(this, 1);
        coffee = new Coffee(this, 1);
        milk = new Milk(this, 1);
        cream = new Cream(this, 1);
        chocolate = new Chocolate(this, 1);
    }

    public void addSugar(int quantity) {
        sugar = new Sugar(this, quantity);
    }

    @Override
    public void setAllIngredients() {
        setAllIngredients(new AbstractIngredient[] {water, coffee, milk, cream, chocolate, sugar});
    }
}
