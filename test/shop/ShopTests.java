package test.shop;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import shop.Shop;

@RunWith(JUnit4.class)
public class ShopTests {

    private Shop shop;

    @Before
    public void init() {
        shop = new Shop();
    }

    @Test
    public void testChangeSizeAfterRemoveSweetFromTheBase() {
        String name = "TestSweet";
        shop.addSweetToTheBase(name, 10, 5, "TestCategory");
        int size = shop.getSweets().size();
        shop.removeSweetFromTheBase(name);
        assertEquals(size - 1, shop.getSweets().size());
    }

    @Test
    public void testRemoveSweetFromTheBase() {
        String name = shop.getSweets().get(0).get(Shop.NAME);
        int size = shop.getSweets().size();
        shop.removeSweetFromTheBase(name);
        assertEquals(Shop.FAIL, shop.findSweet(name));
    }

    @Test
    public void testGetSweetsNumericField() {
        String name = "TestSweet";
        shop.addSweetToTheBase(name, 10, 5, "TestCategory");
        int result = shop.getSweetsNumericField(name, Shop.PRICE);
        assertEquals(10, result);
    }

    @Test
    public void testGetSweetsNumericFieldFromTextField() {
        String name = "TestSweet";
        shop.addSweetToTheBase(name, 10, 5, "TestCategory");
        int result = shop.getSweetsNumericField(name, Shop.NAME);
        assertEquals(Shop.FAIL, result);
    }
}
