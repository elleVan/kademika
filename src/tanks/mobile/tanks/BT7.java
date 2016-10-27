package tanks.mobile.tanks;

import tanks.ActionField;
import tanks.helpers.Action;
import tanks.helpers.Direction;
import tanks.fixed.BattleField;
import tanks.mobile.AbstractTank;

public class BT7 extends AbstractTank {

    public BT7(BattleField bf) {
        super(bf);
        speed = super.getSpeed() / 2;
    }

    public BT7(BattleField bf, int x, int y, Direction direction) {
        super(bf, x, y, direction);
        speed = super.getSpeed() / 2;
    }

    @Override
    public Action setUp() {
        return Action.MOVE;
    }
}
