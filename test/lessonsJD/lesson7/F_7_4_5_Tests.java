package test.lessonsJD.lesson7;

import static org.junit.Assert.*;

import lessonsJD.lesson7.F_7_4_5;
import lessonsJD.lesson7.f_7_4_8.Sweet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import shop.Category;
import java.util.HashMap;
import java.util.Map;

@RunWith(JUnit4.class)
public class F_7_4_5_Tests {

    private Sweet sweet;
    private Map<String, Object> map;

    @Before
    public void init() {
        map = new HashMap<>();
        map.put("name", "SomeSweet");
        map.put("quantity", 100);
        map.put("price", 15);
        map.put("inStock", 200);
        map.put("category", Category.CHOCOLATES);
        sweet = new Sweet();
    }

    @Test(expected = NullPointerException.class)
    public void testNullInputObject() throws Exception {
        F_7_4_5.setPrivates(null, map);
    }

    @Test(expected = NullPointerException.class)
    public void testNullInputMap() throws Exception {
        F_7_4_5.setPrivates(sweet, null);
    }

    @Test
    public void testSettingPrivates() throws Exception {
        F_7_4_5.setPrivates(sweet, map);
        assertEquals(sweet.getName(), map.get("name"));
    }

}
