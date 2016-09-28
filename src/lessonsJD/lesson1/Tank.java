package lessonsJD.lesson1;

public class Tank {

    private TankColor color;
    private int crew;
    private int maxSpeed;

    public Tank() {

    }

    public Tank(TankColor color, int crew, int maxSpeed) {
        setColor(color);
        setCrew(crew);
        setMaxSpeed(maxSpeed);
    }

    @Override
    public String toString() {
        return "color: " + color + "; crew: " + crew + "; maxSpeed: " + maxSpeed;
    }

    public void move() {
        System.out.println("move");
    }

    public TankColor getColor() {
        return color;
    }

    public void setColor(TankColor color) {
        this.color = color;
    }

    public int getCrew() {
        return crew;
    }

    public void setCrew(int crew) {
        this.crew = crew;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

}