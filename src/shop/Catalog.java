package shop;

import java.util.HashSet;

public class Catalog {

    public Catalog() {
    }

    public void printCatalog(Shop shop) {
        HashSet<String> categories = shop.getCategories();

        for (String category : categories) {
            System.out.println(category);
            for (String[] sweet : shop.getSweets()) {
                if (sweet != null && Shop.CATEGORY < sweet.length && sweet[Shop.CATEGORY].equals(category)) {
                    System.out.println("\t" + sweet[Shop.NAME]);
                }
            }
        }
    }
}
