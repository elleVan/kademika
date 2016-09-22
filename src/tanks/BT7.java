package tanks;

public class BT7 extends Tank {

    private int speed = super.getSpeed() / 2;

    public BT7(ActionField af, BattleField bf) {
        super(af, bf);
    }

    @Override
    public int getSpeed() {
        return speed;
    }
}
