package tanks.fixed;

public class BattleField {

    public static final int Q_SIZE = 64;

    public static final int Q_MIN = 0;
    public static final int Q_MAX = 8;

    private int bfWidth = 576;
    private int bfHeight = 576;

    private String[] coordinatesAggressor = {"1_1", "5_1", "9_1"};

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


    public String scanQuadrant(int x, int y) {
        return battleField[y][x];
    }

    public void updateQuadrant(int x, int y, String value) {
        battleField[y][x] = value;
    }

    public int getDimensionX() {
        return battleField.length;
    }

    public int getDimensionY() {
        return battleField.length;
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

    public String[][] getBattleField() {
        return battleField;
    }
}
