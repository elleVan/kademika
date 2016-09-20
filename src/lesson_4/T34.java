package lesson_4;

public class T34 extends Tank {

    public T34(TankColor color, int crew) {
        setColor(color);
        setCrew(crew);
        setMaxSpeed(50);
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
