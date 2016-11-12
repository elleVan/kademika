package tanks.mobile.tanks;

import tanks.fixed.AbstractBFElement;
import tanks.helpers.Action;
import tanks.helpers.Direction;
import tanks.fixed.BattleField;
import tanks.mobile.AbstractTank;

import java.util.ArrayList;

public class BT7 extends AbstractTank {

    public BT7(BattleField bf) {
        super(bf);
        speed = super.getSpeed() / 2;
    }

    public BT7(BattleField bf, int x, int y, Direction direction) {
        super(bf, x, y, direction);
        speed = super.getSpeed() / 2;
    }

//    @Override
//    public Action setUp() {
//        Object action = buildPathsPart(4 * 64, 8 * 64);
//        while (!(action instanceof Action)) {
//            turn((Direction) action);
//            action = buildPathsPart(4 * 64, 8 * 64);
//        }
//
//        return (Action) action;
//    }

    @Override
    public Action setUp() {
        if (step >= getPathAll().size()) {
            step = 0;
        }
        while (!(getPathAll().get(step) instanceof Action)) {
            turn((Direction) getPathAll().get(step++));
        }
        if (step >= getPathAll().size()) {
            step = 0;
        }
        return (Action) getPathAll().get(step++);
    }
}
