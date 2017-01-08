package lessonsJD.lesson7;

import shop.Category;

import java.util.HashMap;
import java.util.Map;

public class Demo {

    public static void main(String[] args) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "SomeSweet");
        map.put("quantity", 100);
        map.put("price", 15);
        map.put("inStock", 200);
        map.put("category", Category.CHOCOLATES);

        ApplicationManager am = new ApplicationManager();
        Sweet sweet = am.get(Sweet.class, map);

        System.out.println(sweet.getName() + " - " + sweet.getQuantity() + " - " + sweet.getPrice() + " - "
                + sweet.getInStock() + " - " + sweet.getCategory());
    }
}
