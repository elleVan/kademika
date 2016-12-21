package tanks.mobile;

import tanks.fixed.AbstractBFElement;
import tanks.fixed.BattleField;
import tanks.fixed.bfelements.*;
import tanks.helpers.Action;
import tanks.helpers.Direction;
import tanks.helpers.Mission;
import tanks.mobile.tanks.BT7;
import tanks.mobile.tanks.T34;
import tanks.mobile.tanks.Tiger;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.*;
import java.util.List;

public abstract class AbstractTank implements Tank {

    public static final int TANK_STEP = 1;

    protected int speed = 10;
    private Mission mission;

    private int x;
    private int y;

    private Image[] images;

    private boolean destroyed;
    private int step = 0;

    private List<Object> bfElements;
    private int[] bfIds;
    private List<Object> pathNew;
    List<Object> nexts;

    private List<Object> pathAll;

    protected Color colorTank = new Color(255, 0, 0);
    protected Color colorTower = new Color(0, 255, 0);

    private Direction direction;

    private BattleField bf;

    public AbstractTank(BattleField bf) {
        this(bf, 256, 256, Direction.DOWN, Mission.KILL_EAGLE);
    }

    public AbstractTank(BattleField bf, int x, int y, Direction direction, Mission mission) {
        this.bf = bf;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.mission = mission;
        pathAll = new ArrayList<>();
        setImages(createImages());
    }

    public List<Object> detectEnemy() {
        List<Object> result = new ArrayList<>();
        if (mission == Mission.DEFENDER) {
            for (Object el : bf.getTanks()) {
                if (!((AbstractTank) el).isDestroyed() && el.getClass() != this.getClass()) {
                    result.add(el);
                }
            }
        } else {
            for (Object el : bf.getTanks()) {
                if (((AbstractTank) el).getMission() == Mission.DEFENDER) {
                    result.add(el);
                }
            }
        }
        return result;
    }

    public List<Object> turnToEnemy(List<Object> enemies) {
        List<Object> result = new ArrayList<>();
        for (Object el : enemies) {
            AbstractTank enemy = (AbstractTank) el;
            if (x == enemy.getX() && isQuadrantVisible(enemy.getX(), enemy.getY())) {
                if (y < enemy.getY()) {
                    result.add(Direction.DOWN);
                    return result;
                } else {
                    result.add(Direction.UP);
                    return result;
                }
            } else if (y == enemy.getY() && isQuadrantVisible(enemy.getX(), enemy.getY())) {
                if (x < enemy.getX()) {
                    result.add(Direction.RIGHT);
                    return result;
                } else {
                    result.add(Direction.LEFT);
                    return result;
                }
            }
        }
        return result;
    }

    public boolean isQuadrantVisible(int destX, int destY) {
        int possibleX = x;
        int possibleY = y;

        if (x == destX) {
            if (y < destY) {
                while (!bf.isOccupied(possibleX, possibleY, Direction.DOWN) && !(possibleY == destY)) {
                    possibleY += BattleField.Q_SIZE;
                }
                if (possibleY == destY) {
                    return true;
                }
            } else {
                while (!bf.isOccupied(possibleX, possibleY, Direction.UP) && !(possibleY == destY)) {
                    possibleY -= BattleField.Q_SIZE;
                }
                if (possibleY == destY) {
                    return true;
                }
            }
        } else if (y == destY) {
            if (x < destX) {
                while (!bf.isOccupied(possibleX, possibleY, Direction.RIGHT) && !(possibleX == destX)) {
                    possibleX += BattleField.Q_SIZE;
                }
                if (possibleX == destX) {
                    return true;
                }
            } else {
                while (!bf.isOccupied(possibleX, possibleY, Direction.LEFT) && !(possibleX == destX)) {
                    possibleX -= BattleField.Q_SIZE;
                }
                if (possibleX == destX) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<Object> getRandomPath() {
        step = 0;
        bfElements = new ArrayList<>();
        nexts = new ArrayList<>();
        List<Object> result = new ArrayList<>();
        result.add(bf.scanQuadrant(x / BattleField.Q_SIZE, y / BattleField.Q_SIZE));
        List<Object> variants = findNext(bf.scanQuadrant(x / BattleField.Q_SIZE, y / BattleField.Q_SIZE));
        Random random = new Random();
        int idx = random.nextInt(variants.size());
        result.add(variants.get(idx));
        pathNew = result;
        return result;
    }

    public List<Object> findPath(int a, int b) {
        step = 0;
        pathNew = new ArrayList<>();
        nexts = new ArrayList<>();
        bfElements = new ArrayList<>();
        bfIds = new int[81];
        String quadrant = bf.getQuadrant(x, y);
        int initX = Integer.parseInt(quadrant.split("_")[0]);
        int initY = Integer.parseInt(quadrant.split("_")[1]);
        bfElements.add(bf.scanQuadrant(initX, initY));
        bfIds[0] = 0;
        AbstractBFElement destination = bf.scanQuadrant(a, b);


        int finish = 0;
        int distance = 1;
        AbstractBFElement current = bf.scanQuadrant(initX, initY);
        nexts = findNext(current);
        for (int i = 0; i < nexts.size(); i++) {
            if (isTankInNextQuadrant((AbstractBFElement) nexts.get(i))) {
                nexts.remove(i);
            }
        }
        if (nexts.isEmpty()) {
            pathNew.add(current);
            return pathNew;
        }

        for (int i = 1; i < 81 && finish == 0; i++) {

            for (Object el : nexts) {
                bfElements.add(el);
                bfIds[bfElements.size() - 1] = distance;

                if (destination.equals(el)) {
                    finish = 1;
                }
            }

            if (bfIds[i - 1] != bfIds[i]) {
                distance++;
            }

            if (i == bfElements.size()) {
                pathNew.add(current);
                return pathNew;
            }
            nexts = findNext((AbstractBFElement) bfElements.get(i));
        }

        if (bfElements.contains(destination)) {

            List<Object> nextsBack = findNextBack(destination);
            int last = bfIds[bfElements.indexOf(destination)];
            for (int k = 0; k <= last; k++) {
                pathNew.add(null);
            }
            pathNew.set(last, destination);
            pathNew.set(0, bf.scanQuadrant(initX, initY));
            for (int j = last - 1; j > 0; j--) {
                for (Object el : nextsBack) {
                    if (bfElements.contains(el) && bfIds[bfElements.indexOf(el)] == j) {
                        pathNew.set(j, el);
                        break;
                    }
                }

                nextsBack = findNextBack((AbstractBFElement) pathNew.get(j));
            }
        }

        return pathNew;
    }

    public List<Object> findNext(AbstractBFElement bfElement) {
        List<Object> result = new ArrayList<>();
        AbstractBFElement next;
        for (Direction dir : Direction.values()) {
            if (isNextQuadrantPassable(bfElement.getX(), bfElement.getY(), dir)) {
                next = getNext(bfElement.getX(), bfElement.getY(), dir);
                if (!bfElements.contains(next)) {
                    result.add(next);
                    nexts.add(next);
                }
            }
        }
        return result;
    }

    public List<Object> findNextBack(AbstractBFElement bfElement) {
        List<Object> result = new ArrayList<>();
        AbstractBFElement next;
        for (Direction dir : Direction.values()) {
            if (isNextQuadrantPassable(bfElement.getX(), bfElement.getY(), dir)) {
                next = getNext(bfElement.getX(), bfElement.getY(), dir);
                result.add(next);
            }
        }
        return result;
    }

    public AbstractBFElement getNext(int initX, int initY, Direction dir) {
        if (dir == Direction.UP) {
            initY -= BattleField.Q_SIZE;
        } else if (dir == Direction.DOWN) {
            initY += BattleField.Q_SIZE;
        } else if (dir == Direction.LEFT) {
            initX -= BattleField.Q_SIZE;
        } else {
            initX += BattleField.Q_SIZE;
        }

        return bf.scanQuadrant(initX / BattleField.Q_SIZE, initY / BattleField.Q_SIZE);
    }

    public List<Object> generatePathAll() {
        List<Object> result = new ArrayList<>();

        if (pathNew.size() == 1) {
            result.add(Action.NONE);
        }

        for (int i = 1; i < pathNew.size(); i++) {
            AbstractBFElement destination = (AbstractBFElement) pathNew.get(i);
            if (destination.getX() < ((AbstractBFElement) pathNew.get(i - 1)).getX()) {
                result.add(Direction.LEFT);
            } else if (destination.getX() > ((AbstractBFElement) pathNew.get(i - 1)).getX()) {
                result.add(Direction.RIGHT);
            } else if (destination.getY() < ((AbstractBFElement) pathNew.get(i - 1)).getY()) {
                result.add(Direction.UP);
            } else {
                result.add(Direction.DOWN);
            }

            if (bf.isOccupied(destination)) {
                result.add(Action.FIRE);
            }

            result.add(Action.MOVE);
        }

        pathAll = result;
        return result;

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
        if (direction == Direction.UP) {
            return new Bullet(this, (x + 25), y, direction);
        } else if (direction == Direction.DOWN) {
            return new Bullet(this, (x + 25), (y + 64), direction);
        } else if (direction == Direction.LEFT) {
            return new Bullet(this, x, y + 25, direction);
        } else {
            return new Bullet(this, x + 64, y + 25, direction);
        }
    }

    @Override
    public void destroy() {
        destroyed = true;
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;
    }

    public boolean isNextQuadrantPassable(int initX, int initY, Direction dir) {

        String quadrant = bf.getQuadrant(initX, initY);
        int destX = Integer.parseInt(quadrant.split("_")[0]);
        int destY = Integer.parseInt(quadrant.split("_")[1]);

        if (dir == Direction.UP) {
            destY -= 1;
        } else if (dir == Direction.DOWN) {
            destY += 1;
        } else if (dir == Direction.LEFT) {
            destX -= 1;
        } else {
            destX += 1;
        }

        if (bf.isQuadrantOnTheField(destX, destY) && (bf.scanQuadrant(destX, destY) instanceof Rock) && (this instanceof Tiger)) {
            return true;
        } else if (bf.isQuadrantOnTheField(destX, destY) && (bf.scanQuadrant(destX, destY) instanceof Eagle) && (mission == Mission.DEFENDER)) {
            return false;
        }

        return bf.isQuadrantOnTheField(destX, destY) && !(bf.scanQuadrant(destX, destY) instanceof Rock && !bf.scanQuadrant(destX, destY).isDestroyed());
    }

    public boolean isTankInNextQuadrant() {
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

        for (Object el : bf.getTanks()) {
            AbstractTank tank = (AbstractTank) el;
            if (!this.equals(tank) && (tank.getX() == (destX * BattleField.Q_SIZE)) && (tank.getY() == destY * BattleField.Q_SIZE)) {
                return true;
            }
        }
        return false;
    }

    public boolean isTankInNextQuadrant(AbstractBFElement bfElement) {

        for (Object el : bf.getTanks()) {
            AbstractTank tank = (AbstractTank) el;
            if (!tank.isDestroyed() && !this.equals(tank) && tank.getX() == bfElement.getX() && tank.getY() == bfElement.getY()) {
                return true;
            }
        }
        return false;
    }

    public Action setUp() {
        if (step >= getPathAll().size()) {
            step = 0;
        }
        for (Object el : turnToEnemy(detectEnemy())) {
            turn((Direction) el);
            return Action.FIRE;
        }

        while (!(getPathAll().get(step) instanceof Action)) {
            turn((Direction) getPathAll().get(step++));
        }

        if (isTankInNextQuadrant()) {
            if (mission == Mission.KILL_EAGLE) {
                findPath(4, 8);
                setPathAll(generatePathAll());
            } else if (mission == Mission.KILL_DEFENDER) {
                for (Object el : bf.getTanks()) {
                    AbstractTank enemy = (AbstractTank) el;
                    if (enemy.getMission() == Mission.DEFENDER) {
                        findPath(enemy.getX() / BattleField.Q_SIZE, enemy.getY() / BattleField.Q_SIZE);
                        setPathAll(generatePathAll());
                    }
                }
            }
            while (!(getPathAll().get(step) instanceof Action)) {
                turn((Direction) getPathAll().get(step++));
            }
        }

        if (step >= getPathAll().size()) {
            step = 0;
        }
        return (Action) getPathAll().get(step++);
    }

    @Override
    public void draw(Graphics g) {
        List<Object> possible = new ArrayList<>();
        possible.add(bf.scanQuadrant(getX() / BattleField.Q_SIZE, getY() / BattleField.Q_SIZE));
        if ((getDirection() == Direction.UP || getDirection() == Direction.DOWN) && getY() / BattleField.Q_SIZE + 1 < 9) {
            possible.add(bf.scanQuadrant(getX() / BattleField.Q_SIZE, getY() / BattleField.Q_SIZE + 1));
        } else if ((getDirection() == Direction.LEFT || getDirection() == Direction.RIGHT) && getX() / BattleField.Q_SIZE + 1 < 9){
            possible.add(bf.scanQuadrant(getX() / BattleField.Q_SIZE + 1, getY() / BattleField.Q_SIZE));
        }

        for (Object quadrant : possible) {
            if (quadrant instanceof Water) {
                Water water = (Water) quadrant;
                g.fillRect(water.getX(), water.getY(), 64, 64);
                g.drawImage(water.getImageBlank(), water.getX(), water.getY(), new ImageObserver() {
                    @Override
                    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                        return false;
                    }
                });
            }
        }

        if (!isDestroyed()) {
            g.drawImage(images[direction.ordinal()], this.getX(), this.getY(), 64, 64, null);
        }

        for (Object quadrant : possible) {
            if (quadrant instanceof Water) {
                Water water = (Water) quadrant;
                water.draw(g);
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

    public BattleField getBf() {
        return bf;
    }

    public List<Object> getPathAll() {
        return pathAll;
    }

    public int getStep() {
        return step;
    }

    public void setPathAll(List<Object> pathAll) {
        this.pathAll = pathAll;
    }

    public Image[] getImages() {
        return images;
    }

    public void setImages(Image[] images) {
        this.images = images;
    }

    public Image[] createImages() {
        return images;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }
}
