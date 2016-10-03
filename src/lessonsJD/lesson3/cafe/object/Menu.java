package lessonsJD.lesson3.cafe.object;

import lessonsJD.lesson3.cafe.object.ingredients.Cream;
import lessonsJD.lesson3.cafe.object.ingredients.Milk;
import lessonsJD.lesson3.cafe.object.ingredients.Sugar;
import lessonsJD.lesson3.cafe.object.menu_items.*;
import lessonsJD.lesson3.cafe.object.optional.ICream;
import lessonsJD.lesson3.cafe.object.optional.IMilk;
import lessonsJD.lesson3.cafe.object.optional.ISugar;

public class Menu {

    private AbstractMenuItem[] menuItems;

    public void initDemoMenu() {
        menuItems = new AbstractMenuItem[] {
                new BlackCoffee(),
                new WhiteCoffee(),
                new Americano(),
                new Cappuccino(),
                new Mocaccino(),
                new BlackTea(),
                new GreenTea(),
                new EarlGrey()
        };
    }

    public void printMenu() {
        int i = 1;
        for (AbstractMenuItem item : menuItems) {
            if (item != null) {
                System.out.printf("%-4d%-15s%-40s%7.2f%n", i++, item.getName(), item.getIngredients(), item.getPrice());
                if (item instanceof ICream) {
                    System.out.printf("%-4s%-15s%-41s%2s%2.2f%n", "", "", "+ Cream", "+ ", (new Cream(item, 1)).getIncrease());
                }
                if (item instanceof IMilk) {
                    System.out.printf("%-4s%-15s%-41s%2s%2.2f%n", "", "", "+ Milk", "+ ", (new Milk(item, 1)).getIncrease());
                }
                if (item instanceof ISugar) {
                    System.out.printf("%-4s%-15s%-41s%2s%2.2f%n", "", "", "+ Sugar", "+ ", (new Sugar(item, 1)).getIncrease());
                }
            }
        }
    }
}
