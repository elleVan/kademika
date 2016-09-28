package lessonsJD.lesson1;

public class Car {
    private String make;
    private String model;
    private Color color;

    public Car(String make, String model, Color color) {
        this.make = make;
        this.model = model;
        this.color = color;
    }

    public void demonstration() {
        System.out.println("make: " + make + "; model: " + model + "; color: " + color);
    }

    public void startEngine() {
        System.out.println("start engine");
    }

    public void stopEngine() {
        System.out.println("stop engine");
    }

    public void go() {
        System.out.println("go");
    }

    public void stop() {
        System.out.println("stop");
    }

    public void turnOnTheLights() {
        System.out.println("turn on the lights");
    }

    public void turnOffTheLights() {
        System.out.println("turn off the lights");
    }

    public void turnOnTheWipers() {
        System.out.println("turn on the wipers");
    }

    public void turnOffTheWipers() {
        System.out.println("turn off the wipers");
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public Color getColor() {
        return color;
    }
}
