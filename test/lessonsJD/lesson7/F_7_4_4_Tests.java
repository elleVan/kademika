package test.lessonsJD.lesson7;

import lessonsJD.lesson7.F_7_4_4;
import lessonsJD.lesson7.f_7_4_8.Sweet;
import shop.Category;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class F_7_4_4_Tests {

    private List<Object> list;

    @Before
    public void init() {
        list = new ArrayList<>();
        list.add("SomeSweet");
        list.add(100);
        list.add(15);
        list.add(200);
        list.add(Category.CHOCOLATES);
    }

    @Test(expected = NullPointerException.class)
    public void testNullInputClass() {
        F_7_4_4.initClass(null, list);
    }

    @Test
    public void testNullInputList() {
        assertNull(F_7_4_4.initClass(Sweet.class, null));
    }

    @Test
    public void testWithEmptyList() {
        list.clear();
        F_7_4_4.initClass(Sweet.class, list);
    }

    @Test
    public void testWithNulls() {
        list.clear();
        list.add(null);
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(null);
        F_7_4_4.initClass(Sweet.class, list);
    }

    @Test
    public void testParameter() {
        Sweet sweet = F_7_4_4.initClass(Sweet.class, list);
        assertEquals(list.get(0), sweet.getName());
    }
}
