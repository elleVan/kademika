package lessonsJD.lesson7;

import shop.Category;
import shop.Sweet;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class F_7_4_5 {

    public static void main(String[] args) throws Exception {

        Map<String, Object> map = new HashMap<>();
        map.put("name", "SomeSweet");
        map.put("quantity", 100);
//        map.put("price", 15);
        map.put("inStock", 200);
        map.put("category", Category.CHOCOLATES);

        Sweet sweet = new Sweet();
        setPrivates(sweet, map);

        System.out.println(sweet.getName() + " - " + sweet.getQuantity() + " - " + sweet.getPrice() + " - "
                + sweet.getInStock() + " - " + sweet.getCategory());
    }

    public static void setPrivates(Object obj, Map<String, Object> params) throws Exception {

        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            Object value = params.get(field.getName());
            if (value != null) {
                field.set(obj, value);
            }
        }
    }
}