package tanks;

public class Tiger extends AbstractTank {

    private int armor;

    public Tiger(ActionField af, BattleField bf) {
        super(af, bf);
        setArmor(1);
    }

    public Tiger(ActionField af, BattleField bf, int x, int y, Direction direction) {
        super(af, bf, x, y, direction);
        setArmor(1);
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

    public void setArmor(int armor) {
        this.armor = armor;
    }
}
