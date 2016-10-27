package tanks.fixed;

import tanks.fixed.bfelements.*;
import tanks.helpers.Destroyable;
import tanks.helpers.Direction;
import tanks.helpers.Drawable;

import java.awt.*;

public class BattleField implements Drawable {

    public static final int Q_SIZE = 64;

    public static final int Q_MIN = 0;
    public static final int Q_MAX = 8;

    private int bfWidth = 576;
    private int bfHeight = 576;

    private String[] coordinatesAggressor = {"1_1", "5_1", "9_1"};

    private AbstractBFElement[][] battleFieldObj;

    private String[][] battleField = {
            { " ", " ", " ", " ", " ", " ", " ", " ", " " },
            { " ", "B", "B", "R", "R", "R", "B", "B", " " },
            { " ", " ", " ", " ", " ", " ", " ", " ", " " },
            { " ", "B", "B", "B", "W", "B", "B", "B", " " },
            { " ", " ", " ", "W", "W", "W", " ", " ", " " },
            { " ", "B", "B", "B", "W", "B", "B", "B", " " },
            { " ", " ", " ", " ", " ", " ", " ", " ", " " },
            { " ", "R", "R", "B", "R", "B", "R", "R", " " },
            { " ", " ", " ", "B", "E", "B", " ", " ", " " } };


    public BattleField() {
        generateBFObj();
    }

    private void generateBFObj() {
        battleFieldObj = new AbstractBFElement[battleField.length][battleField.length];
        for (int i = 0; i < battleField.length; i++) {
            for (int j = 0; j < battleField[i].length; j++) {
                String coordinates = getQuadrantXY(j + 1, i + 1);
                int x = Integer.parseInt(coordinates.split("_")[0]);
                int y = Integer.parseInt(coordinates.split("_")[1]);

                if (battleField[i][j].trim().isEmpty()) {
                    battleFieldObj[i][j] = new Blank(x, y);
                } else if (battleField[i][j].equals("B")) {
                    battleFieldObj[i][j] = new Brick(x, y);
                } else if (battleField[i][j].equals("E")) {
                    battleFieldObj[i][j] = new Eagle(x, y);
                } else if (battleField[i][j].equals("R")) {
                    battleFieldObj[i][j] = new Rock(x, y);
                } else if (battleField[i][j].equals("W")) {
                    battleFieldObj[i][j] = new Water(x, y);
                }
            }
        }
    }

    public boolean isOccupied(int x, int y, Direction direction) {

        String quadrant = getQuadrant(x, y);
        x = Integer.parseInt(quadrant.split("_")[0]);
        y = Integer.parseInt(quadrant.split("_")[1]);

        if (direction == Direction.UP) {
            y -= 1;
        } else if (direction == Direction.DOWN) {
            y += 1;
        } else if (direction == Direction.LEFT) {
            x -= 1;
        } else {
            x += 1;
        }

        if (isQuadrantOnTheField(x, y)) {
            if (!isQuadrantEmpty(x, y)) {
                return true;
            }
        }

        return false;

    }

    @Override
    public void draw(Graphics g) {
        for (int j = 0; j < battleFieldObj.length; j++) {
            for (int k = 0; k < battleFieldObj.length; k++) {
                battleFieldObj[j][k].draw(g);
            }
        }
    }

    public String getQuadrant(int x, int y) {
        return x / Q_SIZE + "_" + y / Q_SIZE;
    }

    public String getQuadrantXY(int x, int y) {
        return (x - 1) * Q_SIZE + "_" + (y - 1) * Q_SIZE;
    }

    private boolean isQuadrantOnTheField(int x, int y) {
        return (y >= Q_MIN && y <= Q_MAX && x >= Q_MIN && x <= Q_MAX);
    }

    public boolean isQuadrantEmpty(int x, int y) {
        AbstractBFElement bfElement = scanQuadrant(x, y);
        return (bfElement instanceof Blank && bfElement.isDestroyed());
    }

    public AbstractBFElement scanQuadrant(int x, int y) {
        return battleFieldObj[y][x];
    }

    public void destroyObject(int x, int y) {
        battleFieldObj[y][x].destroy();
    }

    public int getBfWidth() {
        return bfWidth;
    }

    public int getBfHeight() {
        return bfHeight;
    }

    public String[] getCoordinatesAggressor() {
        return coordinatesAggressor;
    }
}
