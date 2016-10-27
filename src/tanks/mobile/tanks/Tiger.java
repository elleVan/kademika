package tanks.mobile.tanks;

import tanks.ActionField;
import tanks.helpers.Action;
import tanks.helpers.Direction;
import tanks.fixed.BattleField;
import tanks.mobile.AbstractTank;

public class Tiger extends AbstractTank {

    private int armor;

    public Tiger(BattleField bf) {
        super(bf);
        armor = 1;
    }

    public Tiger(BattleField bf, int x, int y, Direction direction) {
        super(bf, x, y, direction);
        armor = 1;
    }

    @Override
    public void destroy() {
        if (armor > 0) {
            armor--;
        } else {
            super.destroy();
        }

    }

    @Override
    public Action setUp() {
        return Action.FIRE;
    }
}
