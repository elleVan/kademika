package lessonsJD.lesson_1;

public class T34 extends Tank {

    public T34(TankColor color, int crew) {
        super(color, crew, 50);
    }

    @Override
    public String toString() {
        return "T34 - " + super.toString();
    }

    @Override
    public void move() {
        System.out.print("T34 ");
        super.move();
    }
}
