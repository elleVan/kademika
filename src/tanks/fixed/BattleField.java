package tanks.fixed;

import tanks.fixed.bfelements.*;
import tanks.helpers.Destroyable;
import tanks.helpers.Direction;
import tanks.helpers.Drawable;
import tanks.mobile.AbstractTank;

import java.awt.*;
import java.util.List;
import java.util.*;

public class BattleField implements Drawable {

    public static final int Q_SIZE = 64;

    public static final int Q_MIN = 0;
    public static final int Q_MAX = 8;

    private List<Object> tanks;
    private AbstractBFElement eagle;

    private AbstractTank defender;
    private AbstractTank killEagle;
    private AbstractTank killDefender;

    private AbstractBFElement[][] battleFieldObj;

    private List<Object> aroundEagle;
    private List<Object> waters;

    private long gameId;

    private String[][] battleField = {
            { " ", " ", " ", " ", " ", " ", " ", " ", " " },
            { " ", "B", "B", "R", "R", "R", "B", "B", " " },
            { " ", " ", "B", " ", " ", " ", "B", " ", " " },
            { " ", "B", "B", "B", "W", "B", "B", "B", " " },
            { " ", " ", " ", "W", "W", "W", " ", " ", " " },
            { " ", "B", "B", "B", "W", "B", "B", "B", " " },
            { " ", " ", "B", " ", " ", " ", "B", " ", " " },
            { " ", "R", "R", "B", "R", "B", "R", "R", " " },
            { " ", " ", " ", "R", "E", "B", " ", " ", " " } };


    public BattleField() {
        generateBFObj();
        tanks = new ArrayList<>();
        gameId = System.currentTimeMillis();
    }

    public BattleField(String[][] battleField) {
        this.battleField = battleField;
        generateBFObj();
        tanks = new ArrayList<>();
        gameId = System.currentTimeMillis();
    }

    private void generateBFObj() {
        battleFieldObj = new AbstractBFElement[battleField.length][battleField.length];
        aroundEagle = new ArrayList<>();
        waters = new ArrayList<>();

        for (int i = 0; i < battleField.length; i++) {
            for (int j = 0; j < battleField[i].length; j++) {
                int x = j * BattleField.Q_SIZE;
                int y = i * BattleField.Q_SIZE;

                if (battleField[i][j].trim().isEmpty()) {
                    battleFieldObj[i][j] = new Blank(x, y);
                } else if (battleField[i][j].equals("B")) {
                    battleFieldObj[i][j] = new Brick(x, y);
                } else if (battleField[i][j].equals("E")) {
                    battleFieldObj[i][j] = new Eagle(x, y);
                    eagle = battleFieldObj[i][j];
                } else if (battleField[i][j].equals("R")) {
                    battleFieldObj[i][j] = new Rock(x, y);
                } else if (battleField[i][j].equals("W")) {
                    battleFieldObj[i][j] = new Water(x, y);
                    waters.add(battleFieldObj[i][j]);
                }
            }
        }

        aroundEagle.add(battleFieldObj[8][3]);
        aroundEagle.add(battleFieldObj[8][4]);
        aroundEagle.add(battleFieldObj[8][5]);
        aroundEagle.add(battleFieldObj[7][3]);
        aroundEagle.add(battleFieldObj[7][4]);
        aroundEagle.add(battleFieldObj[7][5]);
    }

    public boolean isOccupied(AbstractBFElement bfElement) {

        return !isQuadrantEmpty(bfElement.getX() / BattleField.Q_SIZE, bfElement.getY() / BattleField.Q_SIZE)
                    && !(bfElement instanceof Water);

    }

    public boolean isOccupied(int x, int y, Direction direction) {

        x = x / BattleField.Q_SIZE;
        y = y / BattleField.Q_SIZE;

        if (direction == Direction.UP) {
            y -= 1;
        } else if (direction == Direction.DOWN) {
            y += 1;
        } else if (direction == Direction.LEFT) {
            x -= 1;
        } else {
            x += 1;
        }

        return isQuadrantOnTheField(x, y) && !isQuadrantEmpty(x, y) && !(scanQuadrant(x, y) instanceof Water);
    }

    @Override
    public void draw(Graphics g) {
        for (AbstractBFElement[] arr : battleFieldObj) {
            for (AbstractBFElement el : arr) {
                el.draw(g);
            }
        }
    }

    public boolean isQuadrantOnTheField(int x, int y) {
        return (y >= Q_MIN && y <= Q_MAX && x >= Q_MIN && x <= Q_MAX);
    }

    private boolean isQuadrantEmpty(int x, int y) {
        AbstractBFElement bfElement = scanQuadrant(x, y);
        return (bfElement instanceof Blank || bfElement.isDestroyed());
    }

    public AbstractBFElement scanQuadrant(int x, int y) {
        return battleFieldObj[y][x];
    }

    public void destroyObject(int x, int y) {
        if (scanQuadrant(x, y) instanceof Destroyable) {
            ((Destroyable) battleFieldObj[y][x]).destroy();
        }
    }

    public List<Object> getTanks() {
        return new ArrayList<>(tanks);
    }

    public void addTank(AbstractTank tank) {
        tanks.add(tank);
    }

    public void removeTank(AbstractTank tank) {
        tanks.remove(tank);
    }

    public AbstractBFElement getEagle() {
        return eagle;
    }

    public AbstractTank getDefender() {
        return defender;
    }

    public void setDefender(AbstractTank defender) {
        this.defender = defender;
    }

    public AbstractTank getKillEagle() {
        return killEagle;
    }

    public void setKillEagle(AbstractTank killEagle) {
        this.killEagle = killEagle;
    }

    public AbstractTank getKillDefender() {
        return killDefender;
    }

    public void setKillDefender(AbstractTank killDefender) {
        this.killDefender = killDefender;
    }

    public List<Object> getAroundEagle() {
        return aroundEagle;
    }

    public List<Object> getWaters() {
        return waters;
    }

    public long getGameId() {
        return gameId;
    }
}
