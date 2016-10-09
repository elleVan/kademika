package tanks;

import tanks.fixed.*;
import tanks.fixed.bfelements.*;
import tanks.helpers.Destroyable;
import tanks.helpers.Direction;
import tanks.mobile.AbstractTank;
import tanks.mobile.Bullet;
import tanks.mobile.tanks.T34;
import tanks.mobile.tanks.Tiger;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class ActionField extends JPanel {

    public static final boolean COLORED_MODE = false;

    private BattleField bf;
    private AbstractTank defender;
    private AbstractTank aggressor;
    private Bullet bullet;

    private AbstractBFElement[][] battleFieldObj;

    public ActionField() {
        bf = new BattleField();
        defender = new T34(this, bf);
        bullet = new Bullet(defender, -100, -100, Direction.NONE);
        battleFieldObj = generateBFObj(bf.getBattleField());

        newAggressor();

        JFrame frame = new JFrame("BATTLE FIELD");
        frame.setLocation(750, 150);
        frame.setMinimumSize(new Dimension(bf.getBfWidth() + 16, bf.getBfHeight() + 38));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);
    }

    public void runTheGame() throws InterruptedException {


        while (true) {
            String aggrQuad = getQuadrant(aggressor.getX(), aggressor.getY());
            defender.moveToQuadrant(Integer.parseInt(aggrQuad.split("_")[0]) + 1,
                    Integer.parseInt(aggrQuad.split("_")[1]) + 1);
        }

//        defender.clean();
//        defender.moveRandom();
    }

    public void newAggressor() {
        String aggrCoord = getRandomEmptyQuadrantInTheTopXY();
        aggressor = new Tiger(this, bf, Integer.parseInt(aggrCoord.split("_")[0]),
                Integer.parseInt(aggrCoord.split("_")[1]), Direction.DOWN);
    }

    private String getCoordinatesAggressor() {
        Random random = new Random();
        String aggrCoord = bf.getCoordinatesAggressor()[random.nextInt(bf.getCoordinatesAggressor().length)];
        aggrCoord = getQuadrantXY(Integer.parseInt(aggrCoord.split("_")[0]), Integer.parseInt(aggrCoord.split("_")[1]));
        return aggrCoord;
    }

    private String getRandomEmptyQuadrantInTheTopXY() {
        Random random = new Random();
        int x = random.nextInt(bf.getDimensionX());
        int y = random.nextInt(4);

        while (!isQuadrantEmpty(x, y)) {
            x = random.nextInt(bf.getDimensionX());
            y = random.nextInt(4);
        }

        return getQuadrantXY(x + 1, y + 1);
    }

    public void processTurn(AbstractTank tank) throws InterruptedException {
        repaint();
    }

    public void processMove(AbstractTank tank) throws InterruptedException {

        int covered = 0;
        int step = AbstractTank.TANK_STEP;
        Direction direction = tank.getDirection();

        if ((direction == Direction.UP && tank.getY() <= 0) || (direction == Direction.DOWN && tank.getY() >= 512) ||
                (direction == Direction.LEFT && tank.getX() <= 0) || (direction == Direction.RIGHT && tank.getX() >= 512)) {
            return;
        }

        while (covered < 64) {
            if (direction == Direction.UP) {
                tank.updateY(-step);
            } else if (direction == Direction.DOWN) {
                tank.updateY(step);
            } else if (direction == Direction.LEFT) {
                tank.updateX(-step);
            } else {
                tank.updateX(step);
            }
            covered += step;

            if (covered + step > BattleField.Q_SIZE) {

                int last = BattleField.Q_SIZE - covered;
                if (direction == Direction.UP) {
                    tank.updateY(-last);
                } else if (direction == Direction.DOWN) {
                    tank.updateY(last);
                } else if (direction == Direction.LEFT) {
                    tank.updateX(-last);
                } else {
                    tank.updateX(last);
                }
                covered += last;
            }

            repaint();
            Thread.sleep(tank.getSpeed());
        }
    }

    public void processFire(Bullet bullet) throws InterruptedException {
        this.bullet = bullet;
        int step = Bullet.STEP;
        Direction direction = bullet.getDirection();

        while (bullet.getX() > -14 && bullet.getX() < 590 && bullet.getY() > -14 && bullet.getY() < 590) {

            if (direction == Direction.UP) {
                bullet.updateY(-step);
            } else if (direction == Direction.DOWN) {
                bullet.updateY(step);
            } else if (direction == Direction.LEFT) {
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

    private boolean processInterception() throws InterruptedException {

        String quadrant = getQuadrant(bullet.getX(), bullet.getY());
        int x = Integer.parseInt(quadrant.split("_")[0]);
        int y = Integer.parseInt(quadrant.split("_")[1]);

        if (isQuadrantOnTheField(x, y)) {
            if (!isCellArrayBFObjEmpty(x, y) && battleFieldObj[y][x] instanceof Destroyable) {
                ((Destroyable) battleFieldObj[y][x]).destroy();
                return true;
            }

            if (bullet.getTank() != aggressor && isTankOnTheQuadrantXY(aggressor, bullet.getX(), bullet.getY())) {
                bullet.destroy();
                aggressor.destroy();
                if (aggressor.getX() == -100 && aggressor.getY() == -100) {
                    aggressor.renovate();
                }
                return true;
            }

            if (bullet.getTank() != defender && isTankOnTheQuadrantXY(defender, bullet.getX(), bullet.getY())) {
                bullet.destroy();
                defender.destroy();
                return true;
            }

        }

        return false;
    }

    public String findDirections() throws InterruptedException {

        String quadrant = getQuadrant(defender.getX(), defender.getY());
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

        String quadrant = getQuadrant(defender.getX(), defender.getY());
        int x = Integer.parseInt(quadrant.split("_")[0]);
        int y = Integer.parseInt(quadrant.split("_")[1]);

        int upBorder = y;
        int downBorder = y;
        int leftBorder = x;
        int rightBorder = x;

        for (int i = BattleField.Q_MIN; i <= BattleField.Q_MAX; i++) {
            for (int j = BattleField.Q_MIN; j <= BattleField.Q_MAX; j++) {
                if (!isCellArrayBFEmpty(j, i)) {
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

    public boolean isOccupied(Direction direction) {

        String quadrant = getQuadrant(defender.getX(), defender.getY());
        int x = Integer.parseInt(quadrant.split("_")[0]);
        int y = Integer.parseInt(quadrant.split("_")[1]);

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

    public boolean isQuadrantEmpty(int x, int y) {
        return isCellArrayBFObjEmpty(x, y) && (defender == null || !isTankOnTheQuadrant(defender, x, y)) &&
                (aggressor == null || !isTankOnTheQuadrant(aggressor, x, y));
    }

    private boolean isTankOnTheQuadrantXY(AbstractTank tank, int x, int y) {
        String quadrant = getQuadrant(x, y);
        String tankQ = getQuadrant(tank.getX(), tank.getY());
        return tankQ.equals(quadrant);
    }

    private boolean isTankOnTheQuadrant(AbstractTank tank, int x, int y) {
        String quadrant = x + "_" + y;
        String tankQ = getQuadrant(tank.getX(), tank.getY());
        return tankQ.equals(quadrant);
    }

    private boolean isQuadrantOnTheField(int x, int y) {
        return (y >= BattleField.Q_MIN && y <= BattleField.Q_MAX && x >= BattleField.Q_MIN && x <= BattleField.Q_MAX);
    }

    public boolean isCellArrayBFEmpty(int x, int y) {
        return bf.scanQuadrant(x, y).trim().isEmpty();
    }

    public boolean isCellArrayBFObjEmpty(int x, int y) {
        return battleFieldObj[y][x].isEmpty();
    }

    public String getQuadrant(int x, int y) {
        return x / BattleField.Q_SIZE + "_" + y / BattleField.Q_SIZE;
    }

    // if input from the program, remember +1 to arguments
    public String getQuadrantXY(int x, int y) {
        return (x - 1) * BattleField.Q_SIZE + "_" + (y - 1) * BattleField.Q_SIZE;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int i = 0;
        Color cc;
        for (int v = 0; v < 9; v++) {
            for (int h = 0; h < 9; h++) {
                if (COLORED_MODE) {
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

        for (AbstractBFElement[] y : battleFieldObj) {
            for (AbstractBFElement element : y) {
                element.draw(g);
            }
        }

        defender.draw(g);
        aggressor.draw(g);

        bullet.draw(g);
    }

    private AbstractBFElement[][] generateBFObj(String[][] battleField) {
        AbstractBFElement[][] result = new AbstractBFElement[battleField.length][battleField.length];
        for (int i = 0; i < battleField.length; i++) {
            for (int j = 0; j < battleField[i].length; j++) {
                String coordinates = getQuadrantXY(j + 1, i + 1);
                int x = Integer.parseInt(coordinates.split("_")[0]);
                int y = Integer.parseInt(coordinates.split("_")[1]);

                if (battleField[i][j].trim().isEmpty()) {
                    result[i][j] = new Brick(x, y, this);
                    ((Destroyable) result[i][j]).destroy();
                } else if (battleField[i][j].equals("B")) {
                    result[i][j] = new Brick(x, y, this);
                } else if (battleField[i][j].equals("E")) {
                    result[i][j] = new Eagle(x, y, this);
                } else if (battleField[i][j].equals("R")) {
                    result[i][j] = new Rock(x, y, this);
                } else if (battleField[i][j].equals("W")) {
                    result[i][j] = new Water(x, y, this);
                }
            }
        }
        return result;
    }

    public Bullet getBullet() {
        return bullet;
    }
}
