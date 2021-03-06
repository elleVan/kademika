package lessonsJD.lesson7.f_7_4_8;

import shop.Category;

@Service
public class Sweet {

    private String name;

    private int quantity;
    private int price;
    private int inStock;

    private Category category;

    public Sweet() {
    }

    public Sweet(String name, int quantity, int price, int inStock, Category category) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.inStock = inStock;
        this.category = category;
    }

    public Sweet(Sweet sweet) {
        if (sweet != null) {
            this.name = sweet.name;
            this.quantity = sweet.quantity;
            this.price = sweet.price;
            this.inStock = sweet.inStock;
            this.category = sweet.category;
        }
    }

    @initService
    public void init() {
        this.name = "Some Sweet";
        this.quantity = 10;
        this.price = 15;
        this.inStock = 200;
        this.category = Category.CHOCOLATES;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
