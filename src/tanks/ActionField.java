package tanks;

import javax.swing.*;
import java.awt.*;

public class ActionField extends JPanel {

    private boolean COLORDED_MODE = false;

    private BattleField bf;
    private Tank tank;
    private Bullet bullet;

    public ActionField() throws Exception {
        bf = new BattleField();
        tank = new Tank(this, bf);
        bullet = new Bullet(-100, -100, -1);

        JFrame frame = new JFrame("BATTLE FIELD, DAY 2");
        frame.setLocation(750, 150);
        frame.setMinimumSize(new Dimension(bf.getBfWidth() + 16, bf.getBfHeight() + 38));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);
    }

    public void runTheGame() throws Exception {

        tank.moveToQuadrant(4, 4);
        tank.clean();
        tank.moveRandom();
    }

    public void processTurn(Tank tank) throws Exception {
        repaint();
    }

    public void processMove(Tank tank) throws Exception {

        int covered = 0;
        int step = tank.getTANK_STEP();
        int direction = tank.getDirection();

        if ((direction == 1 && tank.getY() <= 0) || (direction == 2 && tank.getY() >= 512) ||
                (direction == 3 && tank.getX() <= 0) || (direction == 4 && tank.getX() >= 512)) {
            return;
        }

        while (covered < 64) {
            if (direction == 1) {
                tank.updateY(-step);
            } else if (direction == 2) {
                tank.updateY(step);
            } else if (direction == 3) {
                tank.updateX(-step);
            } else {
                tank.updateX(step);
            }
            covered += step;

            repaint();
            Thread.sleep(tank.getSpeed());
        }
    }

    public void processFire(Bullet bullet) throws Exception {
        this.bullet = bullet;
        int step = bullet.getSTEP();
        int direction = bullet.getDirection();

        while (bullet.getX() > -14 && bullet.getX() < 590 && bullet.getY() > -14 && bullet.getY() < 590) {

            if (direction == 1) {
                bullet.updateY(-step);
            } else if (direction == 2) {
                bullet.updateY(step);
            } else if (direction == 3) {
                bullet.updateX(-step);
            } else {
                bullet.updateX(step);
            }

            if (processInterception()) {
                bullet.destroy();
            }

            repaint();
            Thread.sleep(bullet.getSpeed());
        }
    }

    public boolean processInterception() {

        String quadrant = getQuadrant(bullet.getX(), bullet.getY());
        int x = Integer.parseInt(quadrant.split("_")[0]);
        int y = Integer.parseInt(quadrant.split("_")[1]);

        if (isQuadrantOnTheField(x, y)) {
            if (!isCellEmpty(x, y)) {
                bf.updateQuandrant(x, y, "");
                return true;
            }
        }

        return false;
    }

    public String findDirections() throws Exception {

        String quadrant = getQuadrant(tank.getX(), tank.getY());
        int x = Integer.parseInt(quadrant.split("_")[0]);
        int y = Integer.parseInt(quadrant.split("_")[1]);

        String borders = findExtremeFilledQuadrants();

        int upBorder = Integer.parseInt(borders.split("_")[0]);
        int downBorder = Integer.parseInt(borders.split("_")[1]);
        int leftBorder = Integer.parseInt(borders.split("_")[2]);
        int rightBorder = Integer.parseInt(borders.split("_")[3]);

        int moveTo1;
        int moveTo2;

        if (rightBorder - leftBorder > downBorder - upBorder) {

            if (checkBordersToFindDirections(x, leftBorder, rightBorder)) {
                moveTo1 = leftBorder;
                moveTo2 = rightBorder;
            } else {
                moveTo1 = rightBorder;
                moveTo2 = leftBorder;
            }
            return Integer.toString(moveTo1) + "_" + Integer.toString(moveTo2) + "_horizontal move";

        } else {

            if (checkBordersToFindDirections(y, upBorder, downBorder)) {
                moveTo1 = upBorder;
                moveTo2 = downBorder;
            } else {
                moveTo1 = downBorder;
                moveTo2 = upBorder;
            }
            return Integer.toString(moveTo1) + "_" + Integer.toString(moveTo2) + "_vertical move";
        }

    }

    private boolean checkBordersToFindDirections(int x, int border_1, int border_2) {
        return (border_2 < x || (border_1 < x && x - border_1 < border_2 - x));
    }

    private String findExtremeFilledQuadrants() {

        String quadrant = getQuadrant(tank.getX(), tank.getY());
        int x = Integer.parseInt(quadrant.split("_")[0]);
        int y = Integer.parseInt(quadrant.split("_")[1]);

        int upBorder = y;
        int downBorder = y;
        int leftBorder = x;
        int rightBorder = x;

        for (int i = bf.getQ_MIN(); i <= bf.getQ_MAX(); i++) {
            for (int j = bf.getQ_MIN(); j <= bf.getQ_MAX(); j++) {
                if (!isCellEmpty(j, i)) {
                    if (j < leftBorder) {
                        leftBorder = j;
                    }
                    if (j > rightBorder) {
                        rightBorder = j;
                    }
                    if (i < upBorder) {
                        upBorder = i;
                    }
                    if (i > downBorder) {
                        downBorder = i;
                    }
                }
            }
        }

        return Integer.toString(upBorder) + "_" + Integer.toString(downBorder) + "_" +
                Integer.toString(leftBorder) + "_" + Integer.toString(rightBorder);
    }

    public boolean isOccupied(int direction) {

        String quadrant = getQuadrant(tank.getX(), tank.getY());
        int x = Integer.parseInt(quadrant.split("_")[0]);
        int y = Integer.parseInt(quadrant.split("_")[1]);

        if (direction == 1) {
            y -= 1;
        } else if (direction == 2) {
            y += 1;
        } else if (direction == 3) {
            x -= 1;
        } else {
            x += 1;
        }

        if (isQuadrantOnTheField(x, y)) {
            if (!isCellEmpty(x, y)) {
                return true;
            }
        }

        return false;

    }

    private boolean isQuadrantOnTheField(int x, int y) {
        if (y >= bf.getQ_MIN() && y <= bf.getQ_MAX() && x >= bf.getQ_MIN() && x <= bf.getQ_MAX()) {
            return true;
        }
        return false;
    }

    public boolean isCellEmpty(int x, int y) {
        if (bf.scanQuadrant(x, y).trim().isEmpty()) {
            return true;
        }
        return false;
    }

    public String getQuadrant(int x, int y) {
        return x / bf.getQ_SIZE() + "_" + y / bf.getQ_SIZE();
    }

    // if input from the program, remember +1 to arguments
    public String getQuadrantXY(int x, int y) {
        return (x - 1) * bf.getQ_SIZE() + "_" + (y - 1) * bf.getQ_SIZE();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int i = 0;
        Color cc;
        for (int v = 0; v < 9; v++) {
            for (int h = 0; h < 9; h++) {
                if (COLORDED_MODE) {
                    if (i % 2 == 0) {
                        cc = new Color(252, 241, 177);
                    } else {
                        cc = new Color(233, 243, 255);
                    }
                } else {
                    cc = new Color(180, 180, 180);
                }
                i++;
                g.setColor(cc);
                g.fillRect(h * 64, v * 64, 64, 64);
            }
        }

        for (int j = 0; j < bf.getDimensionY(); j++) {
            for (int k = 0; k < bf.getDimensionX(); k++) {
                if (bf.scanQuadrant(k, j).equals("B")) {
                    String coordinates = getQuadrantXY(j + 1, k + 1);
                    int separator = coordinates.indexOf("_");
                    int y = Integer.parseInt(coordinates.substring(0, separator));
                    int x = Integer.parseInt(coordinates.substring(separator + 1));
                    g.setColor(new Color(0, 0, 255));
                    g.fillRect(x, y, 64, 64);
                }
            }
        }

        g.setColor(new Color(255, 0, 0));
        g.fillRect(tank.getX(), tank.getY(), 64, 64);
        g.setColor(new Color(0, 255, 0));
        if (tank.getDirection() == 1) {
            g.fillRect(tank.getX() + 20, tank.getY(), 24, 34);
        } else if (tank.getDirection() == 2) {
            g.fillRect(tank.getX() + 20, tank.getY() + 30, 24, 34);
        } else if (tank.getDirection() == 3) {
            g.fillRect(tank.getX(), tank.getY() + 20, 34, 24);
        } else {
            g.fillRect(tank.getX() + 30, tank.getY() + 20, 34, 24);
        }

        g.setColor(new Color(255, 255, 0));
        g.fillRect(bullet.getX(), bullet.getY(), 14, 14);
    }


}
