package tanks.mobile;

import tanks.*;
import tanks.fixed.AbstractBFElement;
import tanks.fixed.BattleField;
import tanks.fixed.bfelements.Blank;
import tanks.fixed.bfelements.Rock;
import tanks.fixed.bfelements.Water;
import tanks.helpers.Action;
import tanks.helpers.Destroyable;
import tanks.helpers.Direction;
import tanks.helpers.Drawable;
import tanks.mobile.tanks.BT7;
import tanks.mobile.tanks.Tiger;

import java.awt.*;
import java.util.*;
import java.util.List;

public abstract class AbstractTank2 implements Tank {
/*
    public static final int TANK_STEP = 1;

    protected int speed = 10;
    protected int movePath = 1;

    private ArrayList<Object> actions;

    private int x;
    private int y;

    private boolean destroyed;

    private Set<Object> banned = new HashSet<>();

    private List<Object> path = new ArrayList<>();

    protected Color colorTank = new Color(255, 0, 0);
    protected Color colorTower = new Color(0, 255, 0);

    private Direction direction;

    private BattleField bf;

    public AbstractTank2(BattleField bf) {
        this(bf, 320, 384, Direction.DOWN);
    }

    public AbstractTank2(BattleField bf, int x, int y, Direction direction) {
        this.bf = bf;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void initPath() {
        actions = buildPath(4 * 64, 8 * 64);
    }



    public ArrayList<Object> buildPath(int destX, int destY) {
        ArrayList<Object> result = new ArrayList<>();
        Tank tank = new BT7(bf, x, y, direction);
        Object action = buildPathsPart(destX, destY);
        result.add(action);
        while (x != destX || y != destY) {
            result.add(buildPathsPart(destX, destY));
        }
        return result;
    }

    public Object buildPathsPart(int destX, int destY) {
        int firstX = x;
        int firstY = y;
        Direction firstDirection = direction;

        path = new ArrayList<>();

        if (x < destX) {
            if (!banned.contains(Direction.RIGHT)) {
                x = helper(Direction.RIGHT, x, 1);
            }
        }

        if (x > destX) {
            if (!banned.contains(Direction.LEFT)) {
                x = helper(Direction.LEFT, x, -1);
            }

        }
        if (y < destY ) {
            if (!banned.contains(Direction.DOWN)) {
                y = helper(Direction.DOWN, y, 1);
            }

        }
        if (path.size() == 0) {
            path.add(Action.NONE);
        }

        direction = firstDirection;

        int i = 0;
        for (; i < 4; i++) {
            if (!(path.get(i) instanceof Direction)) {
                break;
            }
        }

        for (int j = 0; j < i; j++) {
            turn((Direction) path.get(j));
        }

        x = firstX;
        y = firstY;

        if ((path.get(0) instanceof Action) && (Action) path.get(0) == Action.MOVE) {
            if (direction == Direction.UP || direction == Direction.DOWN) {
                banned.remove(Direction.LEFT);
                banned.remove(Direction.RIGHT);
            } else {
                banned.remove(Direction.UP);
                banned.remove(Direction.DOWN);
            }
        }

        return path.get(0);
    }

    public int helper(Direction direction, int a, int sign) {
        if (this.direction != direction) {
            path.add(direction);
            this.direction = direction;
        }
        if (bf.isOccupied(x, y, direction) && isNextQuadrantDestroyable(x, y, direction)) {
            path.add(Action.FIRE);
        } else if (bf.isOccupied(x, y, direction) && !isNextQuadrantDestroyable(x, y, direction)) {
            loop();
        }
        path.add(Action.MOVE);

        return a + BattleField.Q_SIZE * sign;
    }

    public void loop() {
        Direction direction;
        Direction bannedDirection;
        if (this.direction == Direction.LEFT || this.direction == Direction.RIGHT) {
            direction = Direction.DOWN;
            bannedDirection = Direction.UP;
        } else {
            direction = Direction.LEFT;
            bannedDirection = Direction.RIGHT;
        }
        banned.add(bannedDirection);
        this.direction = direction;
        path.add(direction);
        if (bf.isOccupied(x, y, direction) && isNextQuadrantDestroyable(x, y, direction)) {
            path.add(Action.FIRE);
        } else if (bf.isOccupied(x, y, direction) && !isNextQuadrantDestroyable(x, y, direction)) {
            loop();
        }
    }

    @Override
    public void turn(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void move() {
    }

    @Override
    public Bullet fire() {
        return new Bullet(this, (x + 25), (y + 25), direction);
    }

    @Override
    public void destroy() {
        destroyed = true;
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;
    }

    public boolean isNextQuadrantDestroyable(int x, int y, Direction direction) {

        String quadrant = bf.getQuadrant(x, y);
        int destX = Integer.parseInt(quadrant.split("_")[0]);
        int destY = Integer.parseInt(quadrant.split("_")[1]);

        if (direction == Direction.UP) {
            destY -= 1;
        } else if (direction == Direction.DOWN) {
            destY += 1;
        } else if (direction == Direction.LEFT) {
            destX -= 1;
        } else {
            destX += 1;
        }

        if (bf.isOccupied(x, y, direction)) {
            if ((bf.scanQuadrant(destX, destY) instanceof Rock) && !(this instanceof Tiger)) {
                return false;
            }
            return true;
        }

        return false;

    }

    @Override
    public void draw(Graphics g) {
        if (!destroyed) {
            g.setColor(colorTank);
            g.fillRect(x, y, 64, 64);
            g.setColor(colorTower);
            if (direction == Direction.UP) {
                g.fillRect(x + 20, y, 24, 34);
            } else if (direction == Direction.DOWN) {
                g.fillRect(x + 20, y + 30, 24, 34);
            } else if (direction == Direction.LEFT) {
                g.fillRect(x, y + 20, 34, 24);
            } else {
                g.fillRect(x + 30, y + 20, 34, 24);
            }
        }
    }

    @Override
    public void updateX(int i) {
        x += i;
    }

    @Override
    public void updateY(int i) {
        y += i;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public int getMovePath() {
        return movePath;
    }

    public BattleField getBf() {
        return bf;
    }

    public ArrayList<Object> getActions() {
        return actions;
    }
    */
}
