package store;

public class Catalog {

    private Sweet[][] sweets = new Sweet[Category.values().length][];

    public Catalog() {
    }

    public void initCatalog(Base base) {
        Category[] categories = Category.values();
        for (int i = 0; i < categories.length; i++) {

            Category category = categories[i];
            System.out.println(category);

            for (Sweet sweet : base.getSweets()) {
                if (sweet != null && sweet.getCategory() == category) {
                    System.out.println("\t" + sweet.name);
                    // filling sweets here
                }
            }
        }
    }
}
