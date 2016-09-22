package store;

public class StoreLauncher {

    public static void main(String[] args) {
        Base base = new Base();
        base.initDemoBase();
        base.getPrices();
        base.getStock();
        base.getNumberOfOrdersForTheLastWeek();
        base.getOrdersOfToday();
        base.printBase();
    }
}
