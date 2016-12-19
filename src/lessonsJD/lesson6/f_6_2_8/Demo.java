package lessonsJD.lesson6.f_6_2_8;

public class Demo {

    public static void main(String[] args) {

        Product p1 = new Newspaper("Daily News");
        Product p2 = new Newspaper("The New York Times");
        Product p3 = new Magazine("Vogue");

        Subscriber s1 = new Subscriber();
        Subscriber s2 = new Subscriber();

        p1.addObserver(s1);
        p2.addObserver(s1);
        p2.addObserver(s2);
        p3.addObserver(s2);

        p1.newEdition();
        System.out.println();

        p2.newEdition();
        System.out.println();

        p3.newEdition();
    }
}
