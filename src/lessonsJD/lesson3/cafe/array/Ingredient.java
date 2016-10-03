package lessonsJD.lesson3.cafe.array;

public class Ingredient {

    private String name;
    private double increasePerKilo;
    private int weightGrams;

    public Ingredient(String name, double increasePerKilo, int weightGrams) {
        this.name = name;
        this.increasePerKilo = increasePerKilo;
        this.weightGrams = weightGrams;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getIncreasePerKilo() {
        return increasePerKilo;
    }

    public void setIncreasePerKilo(double increasePerKilo) {
        this.increasePerKilo = increasePerKilo;
    }

    public int getWeightGrams() {
        return weightGrams;
    }

    public void setWeightGrams(int weightGrams) {
        this.weightGrams = weightGrams;
    }
}
