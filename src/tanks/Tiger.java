package tanks;

public class Tiger extends Tank {

    private int armor;

    public Tiger(ActionField af, BattleField bf) {
        super(af, bf);
        armor = 1;
    }

    public Tiger(ActionField af, BattleField bf, int x, int y, Direction direction) {
        super(af, bf, x, y, direction);
        armor = 1;
    }

    @Override
    public void destroy() throws Exception {
        if (armor > 0) {
            armor--;
        } else {
            super.destroy();
        }

    }

    public int getArmor() {
        return armor;
    }
}
