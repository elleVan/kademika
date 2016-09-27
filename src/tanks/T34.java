package tanks;

import java.awt.*;

public class T34 extends AbstractTank {

    public T34(ActionField af, BattleField bf) {
        super(af, bf);
        colorTank = new Color(0, 210, 0);
        colorTower = new Color(255, 220, 0);
    }

    public T34(ActionField af, BattleField bf, int x, int y, Direction direction) {
        super(af, bf, x, y, direction);
        colorTank = new Color(0, 210, 0);
        colorTower = new Color(255, 220, 0);
    }
}
