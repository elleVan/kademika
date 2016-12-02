package tanks.mobile.tanks;

import tanks.ActionField;
import tanks.helpers.Action;
import tanks.helpers.Direction;
import tanks.fixed.BattleField;
import tanks.mobile.AbstractTank;

import java.awt.*;

public class T34 extends AbstractTank {

    public T34(BattleField bf) {
        super(bf);
        colorTank = new Color(0, 210, 0);
        colorTower = new Color(255, 220, 0);
        setImageName("tank");
    }

    public T34(BattleField bf, int x, int y, Direction direction) {
        super(bf, x, y, direction);
        colorTank = new Color(0, 210, 0);
        colorTower = new Color(255, 220, 0);
        setImageName("tank");
    }

    private Object[] actions = new Object[] {
            Direction.RIGHT,
            Action.FIRE,
//            Action.MOVE,
//            Action.FIRE,
//            Action.MOVE,
//            Action.FIRE,
//            Action.FIRE
    };

    private int step = 0;

    @Override
    public Action setUp() {
        if (step >= actions.length) {
            step = 0;
        }
        if (!(actions[step] instanceof Action)) {
            turn((Direction) actions[step++]);
        }
        if (step >= actions.length) {
            step = 0;
        }
        return (Action) actions[step++];
    }
}
