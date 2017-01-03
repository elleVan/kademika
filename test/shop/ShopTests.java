package test.shop;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import shop.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(JUnit4.class)
public class ShopTests {

    private Shop shop;
    private Sweet testSweet;

    @Before
    public void init() {
        shop = new Shop();
        testSweet = new Sweet("TestSweet", 1, 10, 10, Category.CHOCOLATES);
    }

    @Test
    public void testTransactionWithNullOrder() {
        assertNull(shop.carryOutTransaction(new Customer("Test"), null));
    }

    @Test
    public void testTransactionWithNullCustomer() {
        assertNull(shop.carryOutTransaction(null, new ArrayList<>(Arrays.asList(new Sweet()))));
    }

    @Test
    public void testTransactionWithAbsentSweet() {
        List<Sweet> sweets = new ArrayList<>();
        sweets.add(new Sweet("Sweet", 1, 1, 10, Category.CHOCOLATES));

        assertNull(shop.carryOutTransaction(new Customer("Test"), sweets));
    }

    @Test
    public void testTransactionWithMinusQuantity() {
        shop.addSweetToTheBase(testSweet);

        List<Sweet> sweets = new ArrayList<>();
        sweets.add(shop.chooseSweet(testSweet.getName(), -5));

        assertNull(shop.carryOutTransaction(new Customer("Test"), sweets));
    }

    @Test
    public void testTransactionWithQuantityLessThanInStock() {
        shop.addSweetToTheBase(testSweet);

        List<Sweet> sweets = new ArrayList<>();
        sweets.add(shop.chooseSweet(testSweet.getName(), 15));

        assertNull(shop.carryOutTransaction(new Customer("Test"), sweets));
    }

    @Test
    public void testIncreaseInStockFieldInBasicSweet() {
        shop.addSweetToTheBase(testSweet);
        int inStock = testSweet.getInStock();

        List<Sweet> sweets = new ArrayList<>();
        sweets.add(shop.chooseSweet(testSweet.getName(), 5));

        shop.carryOutTransaction(new Customer("Test"), sweets);
        assertEquals(inStock - 5, shop.findSweet(testSweet.getName()).getInStock());
    }

    @Test
    public void testIncreaseTransactionsAfterTransaction() {
        shop.addSweetToTheBase(testSweet);

        List<Sweet> sweets = new ArrayList<>();
        sweets.add(shop.chooseSweet(testSweet.getName(), 5));

        Transaction transaction = shop.carryOutTransaction(new Customer("Test"), sweets);
        assertTrue(shop.getTransactions().contains(transaction));
    }

    @Test
    public void testIncreaseTransactionsInCustomerAfterTransaction() {
        shop.addSweetToTheBase(testSweet);

        List<Sweet> sweets = new ArrayList<>();
        sweets.add(shop.chooseSweet(testSweet.getName(), 5));

        Customer customer = new Customer("Test");
        Transaction transaction = shop.carryOutTransaction(customer, sweets);

        assertTrue(customer.getTransactions().contains(transaction));
    }

    @Test
    public void testIncreaseCustomersAfterTransactionWithNewCustomer() {
        shop.addSweetToTheBase(testSweet);

        List<Sweet> sweets = new ArrayList<>();
        sweets.add(shop.chooseSweet(testSweet.getName(), 5));

        Customer customer = new Customer("Test");
        assertFalse(shop.getCustomers().contains(customer));

        shop.carryOutTransaction(customer, sweets);
        assertTrue(shop.getCustomers().contains(customer));
    }

    @Test
    public void testRemoveSweetFromTheBase() {
        shop.addSweetToTheBase(testSweet);
        assertTrue(shop.getSweets().contains(testSweet));

        shop.removeSweetFromTheBase(testSweet.getName());
        assertFalse(shop.getSweets().contains(testSweet));
    }
}
