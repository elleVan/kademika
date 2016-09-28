package lessonsJD.lesson1;

public class F_1_5_5 {

    public static void main(String[] args) throws Exception {
        Car car1 = new Car("Toyota", "Avalon Hybrid", Color.MAROON);
        Car car2 = new Car("Jaguar", "XJ", Color.BLUE);

        car1.demonstration();
        car1.startEngine();
        car1.go();
        car1.stop();
        car1.stopEngine();

        car2.demonstration();
    }
}
