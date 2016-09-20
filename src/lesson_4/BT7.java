package lesson_4;

public class BT7 extends Tank {

    public BT7(TankColor color, int crew) {
        setColor(color);
        setCrew(crew);
        setMaxSpeed(72);
    }

    @Override
    public String toString() {
        return "BT7 - " + super.toString();
    }

    @Override
    public void move() {
        System.out.print("BT7 ");
        super.move();
    }
}
