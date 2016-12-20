package shop;

import java.awt.*;
import java.util.Date;

public class ShopLauncher {

    public static void main(String[] args) throws Exception {
        SplashScreen splash = SplashScreen.getSplashScreen();
        Thread.sleep(5000);
        splash.close();

        Shop shop = new Shop();
        shop.run();
        shop.getOrdersForOneDay(new Date());
        shop.printBase();

        ShopUI ui = new ShopUI(shop);
    }
}
