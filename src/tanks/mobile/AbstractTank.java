package tanks.mobile;

import tanks.fixed.AbstractBFElement;
import tanks.fixed.BattleField;
import tanks.fixed.bfelements.*;
import tanks.helpers.Action;
import tanks.helpers.Direction;
import tanks.helpers.Mission;
import tanks.mobile.tanks.Tiger;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public abstract class AbstractTank implements Tank {

    public static final int TANK_STEP = 1;

    protected int speed = 24;
    private Mission mission;

    private int x;
    private int y;

    private Image[] images;

    private boolean destroyed;
    private int step = 0;

    private List<AbstractBFElement> bfElements;
    private int[] bfIds;
    private List<AbstractBFElement> pathQuadrants;
    private List<AbstractBFElement> next;

    private boolean hide;

    private List<Object> pathActions;

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
        pathActions = new ArrayList<>();
        images = createImages();
    }

    private List<AbstractTank> detectEnemy() {

        List<AbstractTank> result = new ArrayList<>();
        if (mission == Mission.DEFENDER) {
            result.add(bf.getKillEagle());
            result.add(bf.getKillDefender());
        } else {
            result.add(bf.getDefender());
        }
        return result;
    }

    private Direction turnToEnemy(List<AbstractTank> enemies) {

        for (AbstractTank enemy : enemies) {
            if (enemy != null) {
                if (enemy.getX() > x - BattleField.Q_SIZE && enemy.getX() < x + BattleField.Q_SIZE
                        && isQuadrantVisible(x, y, enemy.getX(), enemy.getY()) && !hide) {
                    if (y < enemy.getY()) {
                        return Direction.DOWN;
                    } else {
                        return Direction.UP;
                    }
                } else if (enemy.getY() > y - BattleField.Q_SIZE && enemy.getY() < y + BattleField.Q_SIZE
                        && isQuadrantVisible(x, y, enemy.getX(), enemy.getY()) && !hide) {
                    if (x < enemy.getX()) {
                        return Direction.RIGHT;
                    } else {
                        return Direction.LEFT;
                    }
                }
            }
        }
        return null;
    }

    private boolean isQuadrantVisible(int initX, int initY, int destX, int destY) {

        int possibleX = initX;
        int possibleY = initY;

        if (destX > initX - BattleField.Q_SIZE && destX < initX + BattleField.Q_SIZE) {
            if (initY < destY) {
                while (!bf.isOccupied(possibleX, possibleY, Direction.DOWN) && !(possibleY >= destY)) {
                    possibleY += BattleField.Q_SIZE;
                }
                if (possibleY >= destY) {
                    return true;
                }
            } else {
                while (!bf.isOccupied(possibleX, possibleY, Direction.UP) && !(possibleY <= destY)) {
                    possibleY -= BattleField.Q_SIZE;
                }
                if (possibleY <= destY) {
                    return true;
                }
            }
        } else if (destY > initY - BattleField.Q_SIZE && destY < initY + BattleField.Q_SIZE) {
            if (initX < destX) {
                while (!bf.isOccupied(possibleX, possibleY, Direction.RIGHT) && !(possibleX >= destX)) {
                    possibleX += BattleField.Q_SIZE;
                }
                if (possibleX >= destX) {
                    return true;
                }
            } else {
                while (!bf.isOccupied(possibleX, possibleY, Direction.LEFT) && !(possibleX <= destX)) {
                    possibleX -= BattleField.Q_SIZE;
                }
                if (possibleX <= destX) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<AbstractBFElement> getDefenderPath() {

        AbstractTank killDefender = bf.getKillDefender();
        AbstractTank killEagle = bf.getKillEagle();

        if (killEagle != null) {
            bfElements = findPath(4, 8, true);

            List<AbstractBFElement> dangerousArea = new ArrayList<>();

            for (int i = 0; i < bfElements.size() && i < 81; i++) {
                if (bfIds[i] <= 5) {
                    dangerousArea.add(bfElements.get(i));
                }
            }

            for (AbstractBFElement bfElement : dangerousArea) {
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

        pathQuadrants = new ArrayList<>();
        next = new ArrayList<>();
        bfElements = new ArrayList<>();

        pathQuadrants.add(bf.scanQuadrant(x / BattleField.Q_SIZE, y / BattleField.Q_SIZE));

        List<AbstractBFElement> variants = findNext(bf.scanQuadrant(x / BattleField.Q_SIZE, y / BattleField.Q_SIZE));
        List<AbstractBFElement> preferable = new ArrayList<>();

        for (int i = 0; i < variants.size() && killEagle != null && killDefender != null; i++) {
            AbstractBFElement bfElement = variants.get(i);
            if (!isQuadrantVisible(bfElement.getX(), bfElement.getY(), killEagle.getX(), killEagle.getY())
                    && !isQuadrantVisible(bfElement.getX(), bfElement.getY(), killDefender.getX(), killDefender.getY())) {
                preferable.add(bfElement);
            }
        }

        getRandomQuadrant(variants, preferable, false);

        return pathQuadrants;
    }

    private List<AbstractBFElement> getHidePath() {

        pathQuadrants = new ArrayList<>();
        next = new ArrayList<>();
        bfElements = new ArrayList<>();

        pathQuadrants.add(bf.scanQuadrant(x / BattleField.Q_SIZE, y / BattleField.Q_SIZE));

        List<AbstractBFElement> variants = findNext(bf.scanQuadrant(x / BattleField.Q_SIZE, y / BattleField.Q_SIZE));
        List<AbstractBFElement> hideVars = new ArrayList<>();

        for (AbstractBFElement bfElement : variants) {
            if (!isQuadrantVisible(bfElement.getX(), bfElement.getY(), bf.getKillDefender().getX(), bf.getKillDefender().getY())
                    && !isQuadrantVisible(bfElement.getX(), bfElement.getY(), bf.getKillEagle().getX(), bf.getKillEagle().getY())
                    && (bfElement instanceof Blank || bfElement instanceof Water || bfElement.isDestroyed())) {
                hideVars.add(bfElement);
            }
        }

        getRandomQuadrant(variants, hideVars, true);

        return pathQuadrants;
    }

    private void getRandomQuadrant(List<AbstractBFElement> allVariants, List<AbstractBFElement> preferableVariants, boolean hiding) {

        Random random = new Random();
        if (preferableVariants.size() != 0) {
            int idx = random.nextInt(preferableVariants.size());
            pathQuadrants.add(preferableVariants.get(idx));
            if (hiding) {
                hide = true;
            }
        } else {
            int idx = random.nextInt(allVariants.size());
            pathQuadrants.add(allVariants.get(idx));
        }
    }

    public List<AbstractBFElement> findPath(int a, int b) {
        return findPath(a, b, false);
    }

    private List<AbstractBFElement> findPath(int a, int b, boolean fiction) {

        pathQuadrants = new ArrayList<>();
        next = new ArrayList<>();
        bfElements = new ArrayList<>();
        bfIds = new int[81];

        AbstractBFElement current = bf.scanQuadrant(x / BattleField.Q_SIZE, y / BattleField.Q_SIZE);
        AbstractBFElement destination = bf.scanQuadrant(a, b);

        bfElements.add(destination);
        bfIds[0] = 0;

        next = findNext(destination, fiction);

        for (int i = 0; i < 9; i++) {
            if (i != destination.getX() / BattleField.Q_SIZE) {
                AbstractBFElement bfElement = bf.scanQuadrant(i, destination.getY() / BattleField.Q_SIZE);
                if (!next.contains(bfElement) && isQuadrantVisible(destination.getX(), destination.getY(), bfElement.getX(), bfElement.getY())) {
                    next.add(bfElement);
                }
            }
        }
        for (int i = 0; i < 9; i++) {
            if (i != destination.getY() / BattleField.Q_SIZE) {
                AbstractBFElement bfElement = bf.scanQuadrant(destination.getX() / BattleField.Q_SIZE, i);
                if (!next.contains(bfElement) && isQuadrantVisible(destination.getX(), destination.getY(), bfElement.getX(), bfElement.getY())) {
                    next.add(bfElement);
                }
            }
        }

        if (next.isEmpty()) {
            pathQuadrants.add(current);
            return pathQuadrants;
        }

        fillBFElements();

        if (fiction) {
            return bfElements;
        }

        if (bfElements.contains(current)) {
            fillPathQuadrants(destination, current);
            Collections.reverse(pathQuadrants);
        } else {
            pathQuadrants.add(current);
        }

        return pathQuadrants;
    }

    private List<AbstractBFElement> findPathStraight(int a, int b) {

        pathQuadrants = new ArrayList<>();
        next = new ArrayList<>();
        bfElements = new ArrayList<>();
        bfIds = new int[81];

        AbstractBFElement current = bf.scanQuadrant(x / BattleField.Q_SIZE, y / BattleField.Q_SIZE);
        AbstractBFElement destination = bf.scanQuadrant(a, b);

        bfElements.add(destination);
        bfIds[0] = 0;

        next = findNext(current);

        for (int i = 0; i < next.size(); i++) {
            if (isTankInNextQuadrant(next.get(i))) {
                next.remove(i);
            }
        }

        if (next.isEmpty()) {
            pathQuadrants.add(current);
            return pathQuadrants;
        }

        fillBFElements();

        if (bfElements.contains(destination)) {
            fillPathQuadrants(current, destination);
        } else {
            pathQuadrants.add(current);
        }

        return pathQuadrants;
    }

    private void fillBFElements() {

        int distance = 1;

        for (int i = 1; i < 81; i++) {

            for (AbstractBFElement el : next) {
                bfElements.add(el);
                bfIds[bfElements.size() - 1] = distance;
            }

            if (bfIds[i - 1] != bfIds[i]) {
                distance++;
            }

            if (i < bfElements.size()) {
                next = findNext(bfElements.get(i));
            }
        }
    }

    private void fillPathQuadrants(AbstractBFElement first, AbstractBFElement last) {

        List<AbstractBFElement> back = findBack(last);
        int lastId = bfIds[bfElements.indexOf(last)];
        for (int k = 0; k <= lastId; k++) {
            pathQuadrants.add(null);
        }
        pathQuadrants.set(0, first);
        pathQuadrants.set(lastId, last);

        for (int j = lastId - 1; j > 0; j--) {
            for (AbstractBFElement el : back) {
                if (bfElements.contains(el) && bfIds[bfElements.indexOf(el)] == j) {
                    pathQuadrants.set(j, el);
                    break;
                }
            }

            back = findBack(pathQuadrants.get(j));
        }
    }


    private List<AbstractBFElement> findNext(AbstractBFElement bfElement) {
        return findNext(bfElement, false);
    }

    private List<AbstractBFElement> findNext(AbstractBFElement bfElement, boolean fiction) {

        List<AbstractBFElement> result = new ArrayList<>();

        for (Direction dir : Direction.values()) {

            boolean passable = isNextQuadrantPassable(bfElement.getX(), bfElement.getY(), dir, fiction);

            if (passable) {
                AbstractBFElement nextElement = getNext(bfElement.getX(), bfElement.getY(), dir);
                if (!bfElements.contains(nextElement) && !next.contains(nextElement)) {
                    result.add(nextElement);
                    next.add(nextElement);
                }
            }
        }
        return result;
    }

    private List<AbstractBFElement> findBack(AbstractBFElement bfElement) {

        List<AbstractBFElement> result = new ArrayList<>();

        for (Direction dir : Direction.values()) {
            if (isNextQuadrantPassable(bfElement.getX(), bfElement.getY(), dir)) {
                AbstractBFElement next = getNext(bfElement.getX(), bfElement.getY(), dir);
                result.add(next);
            }
        }
        return result;
    }

    private AbstractBFElement getNext(int initX, int initY, Direction dir) {

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

    public List<Object> generatePathActions() {

        step = 0;
        List<Object> result = new ArrayList<>();

        if (pathQuadrants.size() == 1) {
            result.add(Action.NONE);
        }

        for (int i = 1; i < pathQuadrants.size(); i++) {
            AbstractBFElement destination = pathQuadrants.get(i);
            if (destination.getX() < (pathQuadrants.get(i - 1)).getX()) {
                result.add(Direction.LEFT);
            } else if (destination.getX() > (pathQuadrants.get(i - 1)).getX()) {
                result.add(Direction.RIGHT);
            } else if (destination.getY() < (pathQuadrants.get(i - 1)).getY()) {
                result.add(Direction.UP);
            } else {
                result.add(Direction.DOWN);
            }

            if (bf.isOccupied(destination)) {
                result.add(Action.FIRE);
            }

            result.add(Action.MOVE);
        }

        pathActions = result;
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

    private boolean isNextQuadrantPassable(int initX, int initY, Direction dir) {
        return isNextQuadrantPassable(initX, initY, dir, false);
    }

    private boolean isNextQuadrantPassable(int initX, int initY, Direction dir, boolean fiction) {

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

        if (!fiction && bf.isQuadrantOnTheField(destX, destY) && mission == Mission.DEFENDER && bf.getAroundEagle().contains(bf.scanQuadrant(destX, destY)) && !bf.scanQuadrant(destX, destY).isDestroyed()
                && !(bf.scanQuadrant(destX, destY) instanceof Blank)) {
            return false;
        }
        if (bf.isQuadrantOnTheField(destX, destY) && (bf.scanQuadrant(destX, destY) instanceof Rock) && (this instanceof Tiger)) {
            return true;
        }
        return bf.isQuadrantOnTheField(destX, destY) && !(bf.scanQuadrant(destX, destY) instanceof Rock && !bf.scanQuadrant(destX, destY).isDestroyed());
    }

    public boolean isTankInNextQuadrant() {

        int minX;
        int maxX;
        int minY;
        int maxY;

        if (direction == Direction.UP) {
            minX = x - BattleField.Q_SIZE;
            maxX = x + BattleField.Q_SIZE;
            minY = y - 2 * BattleField.Q_SIZE;
            maxY = y;
        } else if (direction == Direction.DOWN) {
            minX = x - BattleField.Q_SIZE;
            maxX = x + BattleField.Q_SIZE;
            minY = y + BattleField.Q_SIZE - 1;
            maxY = y + 2 * BattleField.Q_SIZE;
        } else if (direction == Direction.LEFT) {
            minX = x - 2 * BattleField.Q_SIZE;
            maxX = x;
            minY = y - BattleField.Q_SIZE;
            maxY = y + BattleField.Q_SIZE;
        } else {
            minX = x + BattleField.Q_SIZE - 1;
            maxX = x + 2 * BattleField.Q_SIZE;
            minY = y - BattleField.Q_SIZE;
            maxY = y + BattleField.Q_SIZE;
        }

        for (Object el : bf.getTanks()) {
            AbstractTank tank = (AbstractTank) el;
            if (!this.equals(tank) && (tank.getX() > minX) && (tank.getX() < maxX)
                    && (tank.getY() > minY) && (tank.getY() < maxY)) {
                return true;
            }
        }
        return false;
    }

    private boolean isTankInNextQuadrant(AbstractBFElement bfElement) {

        int minX = bfElement.getX() - BattleField.Q_SIZE;
        int maxX = bfElement.getX() + BattleField.Q_SIZE;
        int minY = bfElement.getY() - BattleField.Q_SIZE;
        int maxY = bfElement.getY() + BattleField.Q_SIZE;

        for (Object el : bf.getTanks()) {
            AbstractTank tank = (AbstractTank) el;
            if (!this.equals(tank) && (tank.getX() > minX) && (tank.getX() < maxX)
                    && (tank.getY() > minY) && (tank.getY() < maxY)) {
                return true;
            }
        }
        return false;
    }

    public Action setUp() {

        if (step >= getPathActions().size()) {
            step = 0;
        }

        Direction toEnemy = turnToEnemy(detectEnemy());

        if (toEnemy != null) {
            turn(toEnemy);
            writeToFile(Action.FIRE);
            return Action.FIRE;
        }

        while (!(getPathActions().get(step) instanceof Action)) {
            turn((Direction) getPathActions().get(step++));
        }

        if (isTankInNextQuadrant()) {
            if (mission == Mission.KILL_EAGLE) {
                findPathStraight(4, 8);
                setPathActions(generatePathActions());
            } else if (mission == Mission.KILL_DEFENDER) {
                findPathStraight(bf.getDefender().getX() / BattleField.Q_SIZE, bf.getDefender().getY() / BattleField.Q_SIZE);
                setPathActions(generatePathActions());
            }

            while (!(getPathActions().get(step) instanceof Action)) {
                turn((Direction) getPathActions().get(step++));
            }
        }

        if (step >= getPathActions().size()) {
            step = 0;
        }

        if (getPathActions().get(step) == Action.MOVE) {
            hide = false;
        }

        writeToFile((Action) getPathActions().get(step));
        return (Action) getPathActions().get(step++);
    }

    public void writeToFile(Action action) {

        StringBuilder str = new StringBuilder();
        if (getMission() == Mission.DEFENDER) {
            str.append("D");
        } else if (getMission() == Mission.KILL_EAGLE) {
            str.append("E");
        } else {
            str.append("A");
        }

        if (action == Action.MOVE) {
            str.append("M");
        } else if (action == Action.PIXEL_MOVE) {
            str.append("P");
        } else if (action == Action.WAIT) {
            str.append("W");
        } else {
            str.append("F");
        }

        if (direction == Direction.DOWN) {
            str.append("D");
        } else if (direction == Direction.UP) {
            str.append("U");
        } else if (direction == Direction.RIGHT) {
            str.append("R");
        } else {
            str.append("L");
        }

//        try (
//                FileWriter fw = new FileWriter("src/tanks/logs/log" + bf.getGameId() + ".txt", true);
//                BufferedWriter bw = new BufferedWriter(fw);
//                PrintWriter writer = new PrintWriter(bw)
//        ) {
//            writer.println(str.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

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

    public List<Object> getPathActions() {
        return pathActions;
    }

    public void setPathActions(List<Object> pathActions) {
        this.pathActions = pathActions;
    }

    public Image[] createImages() {
        return images;
    }

    public Mission getMission() {
        return mission;
    }
}
