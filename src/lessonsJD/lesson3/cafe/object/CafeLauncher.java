package lessonsJD.lesson3.cafe.object;

import lessonsJD.lesson3.cafe.object.menu_items.Composite;

public class CafeLauncher {

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.initDemoMenu();
        menu.printMenu();

        Composite composite = new Composite();
        composite.addCoffee(3);
        composite.addSugar(2);
        composite.addChocolate(2);
        composite.addCream(1);

        System.out.println(composite.getIngredients());
        System.out.println(composite.getPrice());
    }
}
