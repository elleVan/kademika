package tanks;

public class BattleField {

    private int Q_SIZE = 64;

    private int Q_MIN = 0;
    private int Q_MAX = 8;

    private int bfWidth = 576;
    private int bfHeight = 576;

    private String[][] battleField = {
            { " ", " ", " ", " ", "B", " ", " ", " ", " " },
            { " ", "B", "B", "B", "B", "B", "B", "B", " " },
            { " ", " ", " ", " ", " ", " ", " ", " ", " " },
            { " ", "B", "B", "B", " ", "B", "B", "B", " " },
            { " ", " ", " ", "B", "B", "B", " ", " ", " " },
            { " ", "B", "B", "B", " ", "B", "B", "B", " " },
            { " ", " ", " ", " ", " ", " ", " ", " ", " " },
            { " ", "B", "B", "B", "B", "B", "B", "B", " " },
            { " ", " ", " ", " ", "B", " ", " ", " ", " " } };

    public String scanQuadrant(int x, int y) {
        return battleField[y][x];
    }

    public void updateQuandrant(int x, int y, String value) {
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

    public int getQ_SIZE() {
        return Q_SIZE;
    }

    public int getQ_MIN() {
        return Q_MIN;
    }

    public int getQ_MAX() {
        return Q_MAX;
    }
}
