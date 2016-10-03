package tanks.mobile.tanks;

import tanks.ActionField;
import tanks.helpers.Direction;
import tanks.fixed.BattleField;
import tanks.mobile.AbstractTank;

public class BT7 extends AbstractTank {

    public BT7(ActionField af, BattleField bf) {
        super(af, bf);
        speed = super.getSpeed() / 2;
    }

    public BT7(ActionField af, BattleField bf, int x, int y, Direction direction) {
        super(af, bf, x, y, direction);
        speed = super.getSpeed() / 2;
    }
}
