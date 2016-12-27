package lessonsJD.lesson7;

import java.util.List;

public class Demo {

    public static void main(String[] args) {

        Container<Tea> container = new Container<>();
        container.add(new GreenTea());

        System.out.println(container.get(0));
    }

}
