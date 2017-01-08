package lessonsJD.lesson7.f_7_4_8;

import java.util.ArrayList;
import java.util.List;

public class Demo {

    public static void main(String[] args) throws Exception {
        List<Object> list = new ArrayList<>();
        list.add("init successfully");

        ApplicationManager am = new ApplicationManager();
        Sweet sweet = am.get(Sweet.class, list);

        System.out.println(sweet.getName() + " - " + sweet.getQuantity() + " - " + sweet.getPrice() + " - "
                + sweet.getInStock() + " - " + sweet.getCategory());
    }
}
