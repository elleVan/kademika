package lessonsJD.lesson3.cafe.object.menu_items;

import lessonsJD.lesson3.cafe.object.AbstractIngredient;
import lessonsJD.lesson3.cafe.object.AbstractMenuItem;
import lessonsJD.lesson3.cafe.object.ingredients.*;

public class Composite extends AbstractMenuItem {

    private Water water;
    private Coffee coffee;
    private Milk milk;
    private Cream cream;
    private Chocolate chocolate;
    private Sugar sugar;
    private BlackTeaLeaf blackTeaLeaf;
    private GreenTeaLeaf greenTeaLeaf;
    private EarlGreyLeaf earlGreyLeaf;

    public Composite() {
        setPrice(3);
    }

    public void addWater(int quantity) {
        water = new Water(this, quantity);
    }

    public void addCoffee(int quantity) {
        coffee = new Coffee(this, quantity);
    }

    public void addMilk(int quantity) {
        milk = new Milk(this, quantity);
    }

    public void addCream(int quantity) {
        cream = new Cream(this, quantity);
    }

    public void addChocolate(int quantity) {
        chocolate = new Chocolate(this, quantity);
    }

    public void addSugar(int quantity) {
        sugar = new Sugar(this, quantity);
    }

    public void addBlackTeaLeaf(int quantity) {
        blackTeaLeaf = new BlackTeaLeaf(this, quantity);
    }

    public void addGreenTeaLeaf(int quantity) {
        greenTeaLeaf = new GreenTeaLeaf(this, quantity);
    }

    public void addEarlGreyLeaf(int quantity) {
        earlGreyLeaf = new EarlGreyLeaf(this, quantity);
    }

    @Override
    public void setAllIngredients() {
        setAllIngredients(new AbstractIngredient[] {
                water, coffee, milk, cream, chocolate, sugar, blackTeaLeaf, greenTeaLeaf, earlGreyLeaf
        });
    }
}
