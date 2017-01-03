package test.shop;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import shop.*;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class DiscountTests {

    private Shop shop;
    private Sweet testSweet;

    @Before
    public void init() {
        shop = new Shop();
        testSweet = new Sweet("TestSweet", 1, 510, 10, Category.CHOCOLATES);
    }

    @Test
    public void sumMoreThan500() {
        shop.addSweetToTheBase(testSweet);

        List<Sweet> sweets = new ArrayList<>();
        sweets.add(shop.chooseSweet(testSweet.getName(), 1));

        Transaction transaction = shop.carryOutTransaction(new Customer("Test"), sweets);

        assertEquals(510 - 510 / 100 * 5, transaction.getSum());
    }

    @Test
    public void sumMoreThan1000() {
        testSweet.setPrice(1100);
        shop.addSweetToTheBase(testSweet);

        List<Sweet> sweets = new ArrayList<>();
        sweets.add(shop.chooseSweet(testSweet.getName(), 1));

        Transaction transaction = shop.carryOutTransaction(new Customer("Test"), sweets);

        assertEquals(1100 - 1100 / 10, transaction.getSum());
    }

    @Test
    public void sumLessThan500() {
        testSweet.setPrice(400);
        shop.addSweetToTheBase(testSweet);

        List<Sweet> sweets = new ArrayList<>();
        sweets.add(shop.chooseSweet(testSweet.getName(), 1));

        Transaction transaction = shop.carryOutTransaction(new Customer("Test"), sweets);

        assertEquals(400, transaction.getSum());
    }

    @Test
    public void negativePrice() {
        testSweet.setPrice(-5);
        shop.addSweetToTheBase(testSweet);

        List<Sweet> sweets = new ArrayList<>();
        sweets.add(shop.chooseSweet(testSweet.getName(), 1));

        assertNull(shop.carryOutTransaction(new Customer("Test"), sweets));
    }
}
