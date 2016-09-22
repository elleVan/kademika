package lesson_4;

public class Tiger extends Tank {

    private int armor;

    public Tiger(TankColor color, int crew) {
        super(color, crew, 36);
        setArmor(1);
    }

    @Override
    public String toString() {
        return "Tiger - " + super.toString() + "; armor: " + armor;
    }

    @Override
    public void move() {
        System.out.print("Tiger ");
        super.move();
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }
}
