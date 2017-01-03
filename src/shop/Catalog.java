package shop;

import java.util.HashSet;

public class Catalog {

    public Catalog() {
    }

    public void printCatalog(Shop shop) {
        HashSet<Category> categories = shop.getCategories();

        for (Category category : categories) {
            if (category != null) {
                System.out.println(category);
                for (Sweet sweet : shop.getSweets()) {
                    if (sweet != null && category.equals(sweet.getCategory())) {
                        System.out.println("\t" + sweet.getName());
                    }
                }
            }
        }
    }
}
