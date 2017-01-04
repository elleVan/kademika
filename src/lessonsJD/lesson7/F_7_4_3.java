package lessonsJD.lesson7;

import shop.Category;
import shop.Sweet;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class F_7_4_3 {

    public static void main(String[] args) throws Exception {

        Map<String, Object> map = new HashMap<>();
        map.put("name", "SomeSweet");
        map.put("quantity", 100);
//        map.put("price", 15);
        map.put("inStock", 200);
        map.put("category", Category.CHOCOLATES);

        Sweet sweet = initClass(Sweet.class, map);

        System.out.println(sweet.getName() + " - " + sweet.getQuantity() + " - " + sweet.getPrice() + " - "
                + sweet.getInStock() + " - " + sweet.getCategory());
    }

    public static <T> T initClass(Class<T> someClass, Map<String, Object> params) throws Exception {

        T result = someClass.newInstance();
        Field[] fields = someClass.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            Object value = params.get(field.getName());
            if (value != null) {
                field.set(result, value);
            }
        }

        return result;
    }
}
