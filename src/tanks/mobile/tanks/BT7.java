package tanks.mobile.tanks;

import tanks.fixed.AbstractBFElement;
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

        AbstractBFElement eagle = getBf().scanQuadrant(4, 8);

        if (getX() < eagle.getX()) {
            if (getDirection() != Direction.RIGHT) {
                turn(Direction.RIGHT);
            }
            if (getBf().isOccupied(getX() + 1, getY(), getDirection())) {
                return Action.FIRE;
            }
            return Action.MOVE;
        } else if (getX() > eagle.getX()) {
            if (getDirection() != Direction.LEFT) {
                turn(Direction.LEFT);
            }
            if (getBf().isOccupied(getX() - 1, getY(), getDirection())) {
                return Action.FIRE;
            }
            return Action.MOVE;
        } else if (getY() < eagle.getY()) {
            if (getDirection() != Direction.DOWN) {
                turn(Direction.DOWN);
            }
            if (getBf().isOccupied(getX(), getY() + 1, getDirection())) {
                return Action.FIRE;
            }
            return Action.MOVE;
        }
        return Action.NONE;
    }
}
