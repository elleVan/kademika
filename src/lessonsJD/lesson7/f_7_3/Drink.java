package lessonsJD.lesson7.f_7_3;

public abstract class Drink {

    private int price;
    private int sugar;

    public Drink() {
    }

    public void addSugar(int quantity) {
        sugar += quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSugar() {
        return sugar;
    }
}
