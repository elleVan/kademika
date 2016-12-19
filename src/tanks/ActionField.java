package tanks;

import tanks.fixed.*;
import tanks.fixed.bfelements.Blank;
import tanks.fixed.bfelements.Rock;
import tanks.fixed.bfelements.Water;
import tanks.helpers.*;
import tanks.mobile.AbstractTank;
import tanks.mobile.Bullet;
import tanks.mobile.Tank;
import tanks.mobile.tanks.BT7;
import tanks.mobile.tanks.T34;

import javax.imageio.ImageIO;
import javax.swing.*;
import tanks.helpers.Action;
import tanks.mobile.tanks.Tiger;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class ActionField extends JPanel {

    public static final boolean COLORED_MODE = false;

    private Image imageBlank;

    private BattleField bf;
    private AbstractTank defender;
    private List<Object> aggressors;
    private Bullet bullet;



    public ActionField() {
        bf = new BattleField();
        defender = new T34(bf);
        bf.addTank(defender);
        bullet = new Bullet(defender, -100, -100, Direction.DOWN);

        aggressors = new ArrayList<>();
        newBT7();
        newTiger();

        try {
            imageBlank = ImageIO.read(new File("blank.jpg"));
        } catch (IOException e) {
            System.err.println("Can't find imageName");
        }

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
            if (!defender.isDestroyed()) {

                for (Object el : aggressors) {
                    AbstractTank aggressor = (AbstractTank) el;
                    if (aggressor instanceof BT7 && (aggressor.getPathAll().size() == aggressor.getStep())) {
                        aggressor.findPath(4, 8);
                        aggressor.setPathAll(aggressor.generatePathAll());
                    } else if (aggressor instanceof Tiger) {
                        aggressor.findPath(defender.getX() / BattleField.Q_SIZE, defender.getY() / BattleField.Q_SIZE);
                        aggressor.setPathAll(aggressor.generatePathAll());
                    }

                    processAction(aggressor.setUp(), aggressor);
                }

            }
            if (!defender.isDestroyed()) {
                if (defender.getPathAll().size() == defender.getStep()) {
                    defender.getRandomPath();
                    defender.setPathAll(defender.generatePathAll());
                }
                processAction(defender.setUp(), defender);
            }
        }
    }

    public void newBT7() {
        AbstractTank aggressor = new BT7(bf, 0, 64, Direction.DOWN);
        aggressors.add(aggressor);
        bf.addTank(aggressor);
    }

    public void newTiger() {
        AbstractTank aggressor = new Tiger(bf, 512, 64, Direction.DOWN);
        aggressors.add(aggressor);
        bf.addTank(aggressor);
    }

    public void processAction(Action a, AbstractTank t) throws InterruptedException {
        if (a == Action.MOVE) {
            processMove(t);
        } else if (a == Action.FIRE) {
                processTurn(t);
                processFire(t.fire());
            }
    }

    public void processTurn(Tank tank) throws InterruptedException {
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

        String tankQuadrant = getQuadrant(tank.getX(), tank.getY());
        int x = Integer.parseInt(tankQuadrant.split("_")[0]);
        int y = Integer.parseInt(tankQuadrant.split("_")[1]);

        if (direction == Direction.DOWN && y < 8) {
            y++;
        } else if (direction == Direction.UP && y > 0) {
            y--;
        } else if (direction == Direction.RIGHT && x < 8) {
            x++;
        } else if (direction == Direction.LEFT && x > 0) {
            x--;
        }

        AbstractBFElement bfElement = bf.scanQuadrant(x, y);
        if (!(bfElement instanceof Blank) && !bfElement.isDestroyed() && !(bfElement instanceof Water)) {
            System.out.println("[illegal move] direction: " + direction + " tankX: " + tank.getX() + ", tankY: " +
                    tank.getY());
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

            if (bf == tank.getBf()) {
                repaint();
                Thread.sleep(tank.getSpeed());
            }

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

            if (bf == bullet.getTank().getBf()) {
                repaint();
                Thread.sleep(bullet.getSpeed());
            }


            if (bullet.isDestroyed()) {
                break;
            }
        }
    }

    private boolean processInterception() throws InterruptedException {

        String quadrant = getQuadrant(bullet.getX(), bullet.getY());
        int x = Integer.parseInt(quadrant.split("_")[0]);
        int y = Integer.parseInt(quadrant.split("_")[1]);

        if (isQuadrantOnTheField(x, y)) {
            AbstractBFElement bfElement = bullet.getTank().getBf().scanQuadrant(x, y);

            if (!bfElement.isDestroyed() && (bfElement instanceof Destroyable)) {
                if (!((bfElement instanceof Rock) && !(bullet.getTank() instanceof Tiger))) {
                    bf.destroyObject(x, y);
                    return true;
                }
                return true;
            }

            for (Object el : aggressors) {
                AbstractTank aggressor = (AbstractTank) el;
                if (bullet.getTank() != aggressor &&
                        checkInterception(getQuadrant(aggressor.getX(), aggressor.getY()), quadrant)) {
                    aggressor.destroy();
                    if (aggressor.isDestroyed()) {
                        aggressors.remove(aggressor);
                        bf.removeTank(aggressor);
                        Thread.sleep(1000);
                        if (aggressor instanceof BT7) {
                            newBT7();
                        } else {
                            newTiger();
                        }
                    }
                    return true;
                }
            }

            if (bullet.getTank() != defender &&
                    checkInterception(getQuadrant(defender.getX(), defender.getY()), quadrant)) {
                defender.destroy();
                return true;
            }

        }

        return false;
    }

    private boolean checkInterception(String object, String quadrant) {
        int ox = Integer.parseInt(object.split("_")[0]);
        int oy = Integer.parseInt(object.split("_")[1]);

        int qx = Integer.parseInt(quadrant.split("_")[0]);
        int qy = Integer.parseInt(quadrant.split("_")[1]);

        if (ox >= 0 && ox < 9 && oy >= 0 && oy < 9) {
            if (ox == qx && oy == qy) {
                return true;
            }
        }
        return false;
    }

    private boolean isQuadrantOnTheField(int x, int y) {
        return (y >= BattleField.Q_MIN && y <= BattleField.Q_MAX && x >= BattleField.Q_MIN && x <= BattleField.Q_MAX);
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
                g.drawImage(imageBlank, h * BattleField.Q_SIZE, v * BattleField.Q_SIZE, new ImageObserver() {
                    @Override
                    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                        return false;
                    }
                });
            }
        }
        bf.draw(g);

        defender.draw(g);
        for (Object el : aggressors) {
            AbstractTank aggressor = (AbstractTank) el;
            if (!aggressor.isDestroyed()) {
                aggressor.draw(g);
            }
        }

        bullet.draw(g);
    }

    public Bullet getBullet() {
        return bullet;
    }
}
