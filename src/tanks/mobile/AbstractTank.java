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

import javax.management.AttributeList;
import java.awt.*;
import java.util.*;
import java.util.List;

public abstract class AbstractTank implements Tank {

    public static final int TANK_STEP = 1;

    protected int speed = 10;
    protected int movePath = 1;

    private int x;
    private int y;

    private boolean isBranching;

    private boolean destroyed;
    protected int step = 0;

    private Set<Object> banned = new HashSet<>();

    private List<Object> path;
    private List<Object> pathAll = new ArrayList<>();
    private List<Object> unfinished = new ArrayList<>();
    private List<Object> paths = new ArrayList<>();

    protected Color colorTank = new Color(255, 0, 0);
    protected Color colorTower = new Color(0, 255, 0);

    private Direction direction;

    private BattleField bf;
    private ActionField af;

    public AbstractTank(BattleField bf) {
        this(bf, 320, 384, Direction.DOWN);
    }

    public AbstractTank(BattleField bf, int x, int y, Direction direction) {
        this.bf = bf;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

//    public List<Object> choosePath(int destX, int destY) {
//        ArrayList<Object> variants = new ArrayList<>();
//
//
//    }

    public HashMap<String, List<Object>> buildPath(int destX, int destY) throws InterruptedException {
        HashMap<String, List<Object>> result = new HashMap<>();


        AbstractTank tank = new BT7(new BattleField(bf.getBattleField()), x, y, direction);
        tank.setAf(this.af);
        tank.setPathAll(this.pathAll);
        ArrayList<Object> list = new ArrayList<>();
        tank.isBranching = false;
        while (tank.x != destX || tank.y != destY) {
            list.add(tank.buildPathsPart(destX, destY));
            tank.getAf().processAction(tank.transform(list), tank);
        }
        if (tank.pathAll.size() == 0) {
            tank.pathAll.add(Action.NONE);
        }
        result.put("pathAll", tank.pathAll);
        result.put("unfinished", tank.unfinished);

        return result;
    }

    public AbstractTank createVirtualTank(HashMap<String, Object> mapTank) throws InterruptedException {
        List<Object> pathTank = (List) mapTank.get("path");
        AbstractTank tank = new BT7(new BattleField(bf.getBattleField()), x, y, direction);
        tank.setAf(this.af);
        tank.pathAll = pathTank;
        do {
            tank.getAf().processAction(tank.transform(pathTank), tank);
        } while (tank.getStep() < pathTank.size());
        tank.banned = (Set) mapTank.get("banned");
        return tank;
    }

    public Action transform(List<Object> list) {

        if (step >= list.size()) {
            step = 0;
        }

        while (!(list.get(step) instanceof Action)) {
            turn((Direction) list.get(step++));
        }

        if (step >= list.size()) {
            step = 0;
        }
        return (Action) list.get(step++);
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

        direction = firstDirection;

        while (!path.isEmpty() && path.get(0) instanceof Direction) {
            turn((Direction) path.get(0));
            pathAll.add(path.get(0));
            path.remove(0);
        }

        if (path.size() == 0) {
            path.add(Action.NONE);
            pathAll.add(Action.NONE);
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
        pathAll.add(path.get(0));
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
        Map<String, Object> map = new HashMap<>();
        map.put("path", new ArrayList<>(pathAll));
        map.put("banned", new HashSet<>(banned));
        if (this.direction == Direction.LEFT || this.direction == Direction.RIGHT) {
            ((List) map.get("path")).add(Direction.UP);
            ((List) map.get("path")).add(Action.NONE);
            ((Set) map.get("banned")).add(Direction.DOWN);
//            if (bf.isOccupied(x, y, Direction.UP) && isNextQuadrantDestroyable(x, y, Direction.UP)) {
//                ((List) map.get("path")).add(Action.FIRE);
//            } else if (!(bf.isOccupied(x, y, Direction.UP) && !isNextQuadrantDestroyable(x, y, Direction.UP))) {
//                ((List) map.get("path")).add(Action.MOVE);
//            } else {
//                ((List) map.get("path")).add(Action.NONE);
//            }

            unfinished.add(map);
            direction = Direction.DOWN;
            bannedDirection = Direction.UP;
        } else {
            ((List) map.get("path")).add(Direction.RIGHT);
            ((List) map.get("path")).add(Action.NONE);
            ((Set) map.get("banned")).add(Direction.LEFT);
//            if (bf.isOccupied(x, y, Direction.RIGHT) && isNextQuadrantDestroyable(x, y, Direction.RIGHT)) {
//                ((List) map.get("path")).add(Action.FIRE);
//            } else if (!(bf.isOccupied(x, y, Direction.RIGHT) && !isNextQuadrantDestroyable(x, y, Direction.RIGHT))) {
//                ((List) map.get("path")).add(Action.MOVE);
//            } else {
//                ((List) map.get("path")).add(Action.NONE);
//            }
            unfinished.add(map);
            direction = Direction.LEFT;
            bannedDirection = Direction.RIGHT;
        }
        isBranching = true;
        path.add(direction);
        banned.add(bannedDirection);

        if (bf.isOccupied(x, y, direction) && isNextQuadrantDestroyable(x, y, direction)) {
            path.add(Action.FIRE);
        } else if (bf.isOccupied(x, y, direction) && !isNextQuadrantDestroyable(x, y, direction)) {
            loop();
        }
    }

    public List<Object> manager(int destX, int destY) throws InterruptedException {
        HashMap<String, List<Object>> map = buildPath(destX, destY);
        paths.add(map.get("pathAll"));
        while (!onlyNulls(map.get("unfinished"))) {
            for (int i = 0; i < map.get("unfinished").size(); i++) {
                if (map.get("unfinished").get(i) instanceof HashMap) {
                    AbstractTank tank = createVirtualTank((HashMap<String, Object>) map.get("unfinished").get(i));
                    paths.add(tank.buildPath(destX, destY).get("pathAll"));
                    map.get("unfinished").set(i, null);
                }
            }
        }
        return (List<Object>) paths.get(1);
    }

    public boolean onlyNulls(Collection collection) {
        for (Object el : collection) {
            if (el != null) {
                return false;
            }
        }
        return true;
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

    public ActionField getAf() {
        return af;
    }

    public void setAf(ActionField af) {
        this.af = af;
    }

    public List<Object> getPathAll() {
        return pathAll;
    }


    public void setPathAll(List<Object> pathAll) {
        this.pathAll = pathAll;
    }

    public int getStep() {
        return step;
    }
}
