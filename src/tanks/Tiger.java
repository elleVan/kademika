package tanks;

public class Tiger extends Tank {

    private int armor;

    public Tiger(ActionField af, BattleField bf) {
        super(af, bf);
    }

    public int getArmor() {
        return armor;
    }
}
