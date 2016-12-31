package shop;

import java.util.HashSet;
import java.util.List;

public class Catalog {

    public Catalog() {
    }

    public void printCatalog(Shop shop) {
        HashSet<String> categories = shop.getCategories();

        for (String category : categories) {
            System.out.println(category);
            for (List<String> sweet : shop.getSweets()) {
                if (sweet != null && Shop.CATEGORY < sweet.size() && sweet.get(Shop.CATEGORY).equals(category)) {
                    System.out.println("\t" + sweet.get(Shop.NAME));
                }
            }
        }
    }
}
