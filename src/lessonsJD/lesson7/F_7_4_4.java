package lessonsJD.lesson7;

import shop.Category;
import shop.Sweet;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.ArrayList;

public class F_7_4_4 {

    public static void main(String[] args) {

        List<Object> list = new ArrayList<>();

        list.add("SomeSweet");
        list.add(100);
        list.add(15);
        list.add(200);
        list.add(Category.CHOCOLATES);

        Sweet sweet = initClass(Sweet.class, list);

        System.out.println(sweet.getName() + " - " + sweet.getQuantity() + " - " + sweet.getPrice() + " - "
                + sweet.getInStock() + " - " + sweet.getCategory());
    }

    public static <T> T initClass(Class<T> someClass, List<Object> params) {

        Object result = null;
        Constructor[] constructors = someClass.getConstructors();
        for (Constructor constructor : constructors) {
            try {
                result = constructor.newInstance(params.toArray());
                break;
            } catch (Exception e) {

            }
        }
        return (T) result;
    }
}