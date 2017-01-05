package lessonsJD.lesson7;

import shop.Category;
import shop.Sweet;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.ArrayList;

public class F_7_4_4 {

    public static void main(String[] args) throws Exception {

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

    public static <T> T initClass(Class<T> someClass, List<Object> params) throws Exception {

        Class[] types = new Class[params.size()];

        for (int i = 0; i < params.size(); i++) {
            types[i] = params.get(i).getClass();
            if (types[i].equals(Integer.class)) {
                types[i] = int.class;
            }
        }

        Constructor constructor = someClass.getConstructor(types);

        return (T) constructor.newInstance(params.toArray());
    }
}