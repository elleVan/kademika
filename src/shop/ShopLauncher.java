package shop;

public class ShopLauncher {

    public static void main(String[] args) {
        Base base = new Base();
        base.getPrices();
        base.getStock();
        base.getNumberOfOrdersForTheLastWeek();
        base.getOrdersOfToday();
        base.printBase();
    }
}
