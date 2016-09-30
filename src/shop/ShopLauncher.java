package shop;

public class ShopLauncher {

    public static void main(String[] args) {
        Shop shop = new Shop();
        shop.run();
        shop.printPrices();
        shop.printStock();
        shop.getNumberOfOrdersForTheLastWeek();
        shop.getOrdersForOneDay(shop.getToday());
        shop.printBase();
    }
}
