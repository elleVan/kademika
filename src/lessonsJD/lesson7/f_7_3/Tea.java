package lessonsJD.lesson7.f_7_3;

public abstract class Tea extends Drink {

    private boolean isStrong;

    public Tea() {
    }

    public boolean isStrong() {
        return isStrong;
    }

    public void setStrong(boolean strong) {
        isStrong = strong;
    }
}
