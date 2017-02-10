package shop;

public class Utils {

    public static void printSweetsInfo(Sweet sweet) {
        System.out.println(sweet.getName() + " - price: " + sweet.getPrice() + ", in stock: " + sweet.getInStock()
                + ", category: " + sweet.getCategory());
    }
}
