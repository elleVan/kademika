package lessonsJD.lesson7.f_7_1;

public class Demo {

    public static void main(String[] args) {

        Container<Drink> container = new Container<>();
        container.add(new GreenTea());

        Drink drink1 = new GreenTea();
        Drink drink2 = new MulledWine();
        Drink drink3 = new GreenTea();

        drink1.setPrice(3);
        drink2.setPrice(2);
        drink3.setPrice(1);

        container.add(drink1);
        container.add(drink2);
        container.add(drink3);

        container.sort();

        for (Drink el : container.getAll()) {
            System.out.println(el.getPrice());
        }
    }

}
