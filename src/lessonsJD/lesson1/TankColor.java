package lessonsJD.lesson1;

/**
 * Created by Mary on 17.09.2016.
 */
public enum TankColor {
    BLACK(0), DARK_GREEN(0), DARK_BLUE(2);

    private int id;

    TankColor(int id) {
        this.id = id;
    }

    static TankColor getDefaultColor() {
        return BLACK;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}