package test.lessonsJD.lesson7.f_7_3;

import lessonsJD.lesson7.f_7_3.Drink;
import lessonsJD.lesson7.f_7_3.MulledWine;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class DrinkTests {

    private Drink drink;

    @Before
    public void init() {
        drink = new MulledWine();
    }

    @Test
    public void testAddSugar() {
        int sugar = drink.getSugar();
        drink.addSugar(2);
        assertEquals(sugar + 2, drink.getSugar());
    }

    @Test
    public void testGetPrice() {
        assertEquals(0, drink.getPrice());
    }

    @Test
    public void testSetPrice() {
        int price = 5;
        drink.setPrice(price);
        assertEquals(price, drink.getPrice());
    }
}
