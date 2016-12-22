package tanks.mobile;

import tanks.fixed.AbstractBFElement;
import tanks.fixed.BattleField;
import tanks.fixed.bfelements.*;
import tanks.helpers.Action;
import tanks.helpers.Direction;
import tanks.helpers.Mission;
import tanks.mobile.tanks.Tiger;

import java.awt.*;
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
    private List<Object> nexts;

    private boolean hide;

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
            result.add(bf.getKillEagle());
            result.add(bf.getKillDefender());
        } else {
            result.add(bf.getDefender());
        }
        return result;
    }

    public List<Object> turnToEnemy(List<Object> enemies) {
        List<Object> result = new ArrayList<>();
        for (Object el : enemies) {
            AbstractTank enemy = (AbstractTank) el;
            if (enemy != null) {
                if (x == enemy.getX() && isQuadrantVisible(x, y, enemy.getX(), enemy.getY()) && !hide) {
                    if (y < enemy.getY()) {
                        result.add(Direction.DOWN);
                        return result;
                    } else {
                        result.add(Direction.UP);
                        return result;
                    }
                } else if (y == enemy.getY() && isQuadrantVisible(x, y, enemy.getX(), enemy.getY()) && !hide) {
                    if (x < enemy.getX()) {
                        result.add(Direction.RIGHT);
                        return result;
                    } else {
                        result.add(Direction.LEFT);
                        return result;
                    }
                }
            }
        }
        return result;
    }

    public boolean isQuadrantVisible(int initX, int initY, int destX, int destY) {
        int possibleX = initX;
        int possibleY = initY;

        if (initX == destX) {
            if (initY < destY) {
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
        } else if (initY == destY) {
            if (initX < destX) {
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

    public List<Object> getDefenderPath() {

        List<Object> result = new ArrayList<>();

        AbstractTank killDefender = bf.getKillDefender();
        AbstractTank killEagle = bf.getKillEagle();

        if (killEagle != null) {
            bfElements = findPath(4, 8, true);

            List<Object> dangerousArea = new ArrayList<>();

            for (int i = 0; i < bfElements.size() && i < 81; i++) {
                if (bfIds[i] <= 5) {
                    dangerousArea.add(bfElements.get(i));
                }
            }

            for (Object el : dangerousArea) {
                AbstractBFElement bfElement = (AbstractBFElement) el;
                if (bfElement.getX() == killEagle.getX() && bfElement.getY() == killEagle.getY()) {
                    return findPath(killEagle.getX() / BattleField.Q_SIZE, killEagle.getY() / BattleField.Q_SIZE);
                }
            }
        }

        if (killDefender != null) {
            if (isQuadrantVisible(x, y, killDefender.getX(), killDefender.getY())) {
                return getHidePath();
            }
        }

        bfElements = new ArrayList<>();
        nexts = new ArrayList<>();
        result.add(bf.scanQuadrant(x / BattleField.Q_SIZE, y / BattleField.Q_SIZE));
        List<Object> variants = findNext(bf.scanQuadrant(x / BattleField.Q_SIZE, y / BattleField.Q_SIZE));
        List<Object> preferable = new ArrayList<>();

        for (int i = 0; i < variants.size() && killEagle != null && killDefender != null; i++) {
            AbstractBFElement bfElement = (AbstractBFElement) variants.get(i);
            if (!isQuadrantVisible(bfElement.getX(), bfElement.getY(), killEagle.getX(), killEagle.getY())
                    && !isQuadrantVisible(bfElement.getX(), bfElement.getY(), killDefender.getX(), killDefender.getY())) {
                preferable.add(bfElement);
            }
        }
        if (preferable.size() != 0) {
            Random random = new Random();
            int idx = random.nextInt(preferable.size());
            result.add(preferable.get(idx));
        } else {
            Random random = new Random();
            int idx = random.nextInt(variants.size());
            result.add(variants.get(idx));
        }

        pathNew = result;
        return result;
    }

    public List<Object> getHidePath() {
        bfElements = new ArrayList<>();
        nexts = new ArrayList<>();
        List<Object> result = new ArrayList<>();
        result.add(bf.scanQuadrant(x / BattleField.Q_SIZE, y / BattleField.Q_SIZE));
        List<Object> variants = findNext(bf.scanQuadrant(x / BattleField.Q_SIZE, y / BattleField.Q_SIZE));
        List<Object> hideVars = new ArrayList<>();
        for (Object el : variants) {
            AbstractBFElement bfElement = (AbstractBFElement) el;
            if (!isQuadrantVisible(bfElement.getX(), bfElement.getY(), bf.getKillDefender().getX(), bf.getKillDefender().getY())
                    && !isQuadrantVisible(bfElement.getX(), bfElement.getY(), bf.getKillEagle().getX(), bf.getKillEagle().getY())
                    && (bfElement instanceof Blank || bfElement instanceof Water || bfElement.isDestroyed())) {
                hideVars.add(el);
            }
        }
        if (hideVars.size() != 0) {
            Random random = new Random();
            int idx = random.nextInt(hideVars.size());
            result.add(hideVars.get(idx));
            hide = true;
        } else {
            Random random = new Random();
            int idx = random.nextInt(variants.size());
            result.add(variants.get(idx));
        }

        pathNew = result;
        return result;
    }

    public List<Object> findPath(int a, int b) {
        return findPath(a, b, false);
    }

    public List<Object> findPath(int a, int b, boolean fiction) {
        pathNew = new ArrayList<>();
        nexts = new ArrayList<>();
        bfElements = new ArrayList<>();
        bfIds = new int[81];
        int initX = x / BattleField.Q_SIZE;
        int initY = y / BattleField.Q_SIZE;

        bfIds[0] = 0;
        AbstractBFElement current = bf.scanQuadrant(initX, initY);


        int finish = 0;
        int distance = 1;
        AbstractBFElement destination = bf.scanQuadrant(a, b);
        nexts = findNext(destination, fiction);
        bfElements.add(destination);

        for (int i = 0; i < destination.getX() / BattleField.Q_SIZE; i++) {
            AbstractBFElement bfElement = bf.scanQuadrant(i, destination.getY() / BattleField.Q_SIZE);
            if (!bfElements.contains(bfElement) && !nexts.contains(bfElement) && isQuadrantVisible(destination.getX(), destination.getY(), bfElement.getX(), bfElement.getY())) {
                nexts.add(bfElement);
            }
        }
        for (int i = destination.getX() / BattleField.Q_SIZE + 1; i < 9; i++) {
            AbstractBFElement bfElement = bf.scanQuadrant(i, destination.getY() / BattleField.Q_SIZE);
            if (!bfElements.contains(bfElement) && !nexts.contains(bfElement) && isQuadrantVisible(destination.getX(), destination.getY(), bfElement.getX(), bfElement.getY())) {
                nexts.add(bfElement);
            }
        }
        for (int i = 0; i < destination.getY() / BattleField.Q_SIZE; i++) {
            AbstractBFElement bfElement = bf.scanQuadrant(destination.getX() / BattleField.Q_SIZE, i);
            if (!bfElements.contains(bfElement) && !nexts.contains(bfElement) && isQuadrantVisible(destination.getX(), destination.getY(), bfElement.getX(), bfElement.getY())) {
                nexts.add(bfElement);
            }
        }
        for (int i = destination.getY() / BattleField.Q_SIZE + 1; i < 9; i++) {
            AbstractBFElement bfElement = bf.scanQuadrant(destination.getX() / BattleField.Q_SIZE, i);
            if (!bfElements.contains(bfElement) && !nexts.contains(bfElement) && isQuadrantVisible(destination.getX(), destination.getY(), bfElement.getX(), bfElement.getY())) {
                nexts.add(bfElement);
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

                if (current.equals(el)) {
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

            if (i < bfElements.size()) {
                nexts = findNext((AbstractBFElement) bfElements.get(i), fiction);
            }
        }

        if (fiction) {
            return bfElements;
        }

        if (bfElements.contains(current)) {

            List<Object> nextsBack = findNextBack(current);
            for (int i = 0; i < nextsBack.size(); i++) {
                if (isTankInNextQuadrant((AbstractBFElement) nextsBack.get(i))) {
                    nextsBack.remove(i);
                }
            }

            if (nextsBack.isEmpty()) {
                pathNew.add(current);
                return pathNew;
            }

            int last = bfIds[bfElements.indexOf(current)];
            for (int k = 0; k <= last; k++) {
                pathNew.add(null);
            }
            pathNew.set(last, destination);
            pathNew.set(0, current);
            for (int j = last - 1; j > 0; j--) {
                for (Object el : nextsBack) {
                    if (bfElements.contains(el) && bfIds[bfElements.indexOf(el)] == j) {
                        pathNew.set(last - j, el);
                        break;
                    }
                }

                nextsBack = findNextBack((AbstractBFElement) pathNew.get(last - j));
            }
        }

        return pathNew;
    }

    public List<Object> findNext(AbstractBFElement bfElement) {
        return findNext(bfElement, false);
    }

    public List<Object> findNext(AbstractBFElement bfElement, boolean fiction) {
        List<Object> result = new ArrayList<>();
        AbstractBFElement next;
        for (Direction dir : Direction.values()) {
            boolean passable;
            if (fiction) {
                passable = isNextQuadrantPassable(bfElement.getX(), bfElement.getY(), dir, true);
            } else {
                passable = isNextQuadrantPassable(bfElement.getX(), bfElement.getY(), dir);
            }
            if (passable) {
                next = getNext(bfElement.getX(), bfElement.getY(), dir);
                if (!bfElements.contains(next) && !nexts.contains(next)) {
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
        step = 0;
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
        return isNextQuadrantPassable(initX, initY, dir, false);
    }

    public boolean isNextQuadrantPassable(int initX, int initY, Direction dir, boolean fiction) {

        int destX = initX / BattleField.Q_SIZE;
        int destY = initY / BattleField.Q_SIZE;

        if (dir == Direction.UP) {
            destY -= 1;
        } else if (dir == Direction.DOWN) {
            destY += 1;
        } else if (dir == Direction.LEFT) {
            destX -= 1;
        } else {
            destX += 1;
        }

        if (!fiction && bf.isQuadrantOnTheField(destX, destY) && mission == Mission.DEFENDER && bf.getNextsEagle().contains(bf.scanQuadrant(destX, destY)) && !bf.scanQuadrant(destX, destY).isDestroyed()
                && !(bf.scanQuadrant(destX, destY) instanceof Blank)) {
            return false;
        }
        if (bf.isQuadrantOnTheField(destX, destY) && (bf.scanQuadrant(destX, destY) instanceof Rock) && (this instanceof Tiger)) {
            return true;
        }
        return bf.isQuadrantOnTheField(destX, destY) && !(bf.scanQuadrant(destX, destY) instanceof Rock && !bf.scanQuadrant(destX, destY).isDestroyed());
    }

    public boolean isTankInNextQuadrant() {
        int destX = x / BattleField.Q_SIZE;
        int destY = y / BattleField.Q_SIZE;

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

        if (getPathAll().get(step) == Action.MOVE) {
            hide = false;
        }

        return (Action) getPathAll().get(step++);
    }

    @Override
    public void draw(Graphics g) {
        if (!isDestroyed()) {
            g.drawImage(images[direction.ordinal()], this.getX(), this.getY(), 64, 64, null);
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

    public void setImages(Image[] images) {
        this.images = images;
    }

    public Image[] createImages() {
        return images;
    }

    public Mission getMission() {
        return mission;
    }
}
