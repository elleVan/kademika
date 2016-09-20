package lesson_4;

public class Tank {

    public TankColor color;
    public int crew;
    public int maxSpeed;

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