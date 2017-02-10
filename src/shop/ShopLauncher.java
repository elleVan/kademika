package shop;

import java.util.Date;
import java.util.List;

public class ShopLauncher {

    public static void main(String[] args) throws Exception {
//        Shop shop = new Shop();
//        shop.run();
//        shop.getOrdersForOneDay(new Date());
//        shop.printBase();
//
//        ShopUI ui = new ShopUI(shop);

        BaseSQL base = new BaseSQL();
        List<Sweet> sweets = base.getSweets();

        for (Sweet sweet : sweets) {
            Utils.printSweetsInfo(sweet);
        }
    }
}
