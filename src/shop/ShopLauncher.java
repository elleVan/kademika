package shop;

import java.util.Date;

public class ShopLauncher {

    public static void main(String[] args) {
        Shop shop = new Shop();
        shop.run();
        shop.getOrdersForOneDay(new Date());
        shop.printBase();

        ShopUI ui = new ShopUI(shop);
    }
}
