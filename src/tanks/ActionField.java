package tanks;

import tanks.fixed.*;
import tanks.fixed.bfelements.Blank;
import tanks.fixed.bfelements.Rock;
import tanks.fixed.bfelements.Water;
import tanks.helpers.*;
import tanks.mobile.AbstractTank;
import tanks.mobile.Bullet;
import tanks.mobile.tanks.BT7;
import tanks.mobile.tanks.T34;

import javax.imageio.ImageIO;
import javax.swing.*;
import tanks.helpers.Action;
import tanks.mobile.tanks.Tiger;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ActionField extends JPanel {

    public static final boolean COLORED_MODE = false;

    private Image imageBlank;

    private BattleField bf;
    private AbstractTank defender;
    private Bullet bullet;

    private Mission missionT34 = Mission.DEFENDER;
    private Mission missionBT7 = Mission.KILL_EAGLE;
    private Mission missionTiger = Mission.KILL_DEFENDER;

    private int defenderX = 256; //256
    private int defenderY = 384; //384

    private int killEagleX = 0;
    private int killEagleY = 64;

    private int killDefenderX = 512;
    private int killDefenderY = 64;

    private int chosen = 0;

    JFrame loadingFrame;
    JFrame gameFrame;

    public ActionField() {
        loadingFrame = createLoadingFrame();

        while (true) {
            gameFrame = createGameFrame();
        }
    }

    public void runTheGame() throws InterruptedException {

here:   while (true) {

            List<Object> tanks = bf.getTanks();
            for (int i = 0; i < tanks.size(); i++) {
                AbstractTank tank = (AbstractTank) tanks.get(i);
                if (!defender.isDestroyed() && !tank.isDestroyed()) {
                    if (tank.getMission() == Mission.KILL_EAGLE && (tank.getPathAll().size() == tank.getStep())) {
                        tank.findPath(4, 8);
                        tank.setPathAll(tank.generatePathAll());
                    } else if (tank.getMission() == Mission.KILL_DEFENDER) {
                        tank.findPath(defender.getX() / BattleField.Q_SIZE, defender.getY() / BattleField.Q_SIZE);
                        tank.setPathAll(tank.generatePathAll());
                    } else if (tank.getMission() == Mission.DEFENDER && tank.getPathAll().size() == tank.getStep()) {
                        tank.getRandomPath();
                        tank.setPathAll(tank.generatePathAll());
                    }

                    processAction(tank.setUp(), tank);
                }
                if (defender.isDestroyed() || bf.getEagle().isDestroyed()) {
                    break here;
                }
            }
        }
    }

    public void newT34() {
        int x;
        int y;
        if (missionT34 == Mission.DEFENDER) {
            x = defenderX;
            y = defenderY;
        } else if (missionT34 == Mission.KILL_EAGLE) {
            x = killEagleX;
            y = killEagleY;
        } else {
            x = killDefenderX;
            y = killDefenderY;
        }

        AbstractTank tank = new T34(bf, x, y, Direction.DOWN, missionT34);
        bf.addTank(tank);
    }

    public void newBT7() {
        int x;
        int y;
        if (missionBT7 == Mission.DEFENDER) {
            x = defenderX;
            y = defenderY;
        } else if (missionBT7 == Mission.KILL_EAGLE) {
            x = killEagleX;
            y = killEagleY;
        } else {
            x = killDefenderX;
            y = killDefenderY;
        }

        AbstractTank tank = new BT7(bf, x, y, Direction.DOWN, missionBT7);
        bf.addTank(tank);
    }

    public void newTiger() {
        int x;
        int y;
        if (missionTiger == Mission.DEFENDER) {
            x = defenderX;
            y = defenderY;
        } else if (missionTiger == Mission.KILL_EAGLE) {
            x = killEagleX;
            y = killEagleY;
        } else {
            x = killDefenderX;
            y = killDefenderY;
        }
        AbstractTank tank = new Tiger(bf, x, y, Direction.DOWN, missionTiger);
        bf.addTank(tank);
    }

    public void processAction(Action a, AbstractTank t) throws InterruptedException {
        if (a == Action.MOVE) {
            processMove(t);
        } else if (a == Action.FIRE) {
                processTurn();
                processFire(t.fire());
            }
    }

    public void processTurn() throws InterruptedException {
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

        int x = tank.getX() / BattleField.Q_SIZE;
        int y = tank.getY() / BattleField.Q_SIZE;

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

        int x = bullet.getX() / BattleField.Q_SIZE;
        int y = bullet.getY() / BattleField.Q_SIZE;

        if (isQuadrantOnTheField(x, y)) {
            AbstractBFElement bfElement = bullet.getTank().getBf().scanQuadrant(x, y);

            if (!bfElement.isDestroyed() && (bfElement instanceof Destroyable)) {
                if (!((bfElement instanceof Rock) && !(bullet.getTank() instanceof Tiger))) {
                    bf.destroyObject(x, y);
                    return true;
                }
                return true;
            }

            List<Object> tanks = bf.getTanks();
            for (int i = 0; i < tanks.size(); i++) {
                AbstractTank tank = (AbstractTank) tanks.get(i);
                if (!tank.isDestroyed() && bullet.getTank() != tank &&
                        checkInterception(tank.getX() / BattleField.Q_SIZE, tank.getY() / BattleField.Q_SIZE, x, y)) {
                    tank.destroy();
                    if (tank.isDestroyed() && tank.getMission() != Mission.DEFENDER) {
                        tanks.remove(tank);
                        Thread.sleep(1000);
                        if (tank instanceof BT7) {
                            newBT7();
                        } else if (tank instanceof Tiger) {
                            newTiger();
                        } else {
                            newT34();
                        }
                    }
                    return true;
                }
            }
        }

        return false;
    }

    private boolean checkInterception(int x1, int y1, int x2, int y2) {

        if (x1 >= 0 && x1 < 9 && y1 >= 0 && y1 < 9) {
            if (x1 == x2 && y1 == y2) {
                return true;
            }
        }
        return false;
    }

    private boolean isQuadrantOnTheField(int x, int y) {
        return (y >= BattleField.Q_MIN && y <= BattleField.Q_MAX && x >= BattleField.Q_MIN && x <= BattleField.Q_MAX);
    }

    private void chooseTanksMissions(int def) {
        if (def == 0) {
            missionT34 = Mission.DEFENDER;
            missionBT7 = Mission.KILL_EAGLE;
            missionTiger = Mission.KILL_DEFENDER;
        } else if (def == 1) {
            missionBT7 = Mission.DEFENDER;
            missionT34 = Mission.KILL_EAGLE;
            missionTiger = Mission.KILL_DEFENDER;
        } else {
            missionTiger = Mission.DEFENDER;
            missionBT7 = Mission.KILL_EAGLE;
            missionT34 = Mission.KILL_DEFENDER;
        }
    }

    public void startTheGame() {
        bf = new BattleField();
        bullet = new Bullet(defender, -100, -100, Direction.DOWN);

        newT34();
        newBT7();
        newTiger();

        for (Object el : bf.getTanks()) {
            AbstractTank tank = (AbstractTank) el;
            if (tank.getMission() == Mission.DEFENDER) {
                defender = tank;
                break;
            }
        }

        try {
            imageBlank = ImageIO.read(new File("blank.jpg"));
        } catch (IOException e) {
            System.err.println("Can't find imageName");
        }
    }

    public JFrame createGameFrame() {
        startTheGame();
        JFrame frame = new JFrame("BATTLE FIELD");
        frame.setLocation(750, 150);
        frame.setMinimumSize(new Dimension(592, 614));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);

        try {
            runTheGame();
        } catch (InterruptedException e) {

        }

        loadingFrame.setVisible(true);
        frame.setVisible(false);
        return frame;
    }

    public JFrame createLoadingFrame() {
        JFrame frame = new JFrame("BATTLE FIELD");
        frame.setLocation(750, 150);
        frame.setMinimumSize(new Dimension(592, 614));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(createLoadingPanel());
        frame.pack();
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);
        return frame;
    }

    private JPanel createLoadingPanel() {
        JPanel panel = new JPanel();

        JLabel lButtons = new JLabel("Choose the tank:");
        JPanel buttons = new JPanel(new GridLayout(0, 1));
        ButtonGroup group = new ButtonGroup();
        ActionListener rbListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chosen = Integer.parseInt(e.getActionCommand());
            }
        };

        JRadioButton button1 = new JRadioButton("T34");
        button1.addActionListener(rbListener);
        button1.setActionCommand("0");
        button1.setSelected(true);
        buttons.add(button1);
        group.add(button1);

        JRadioButton button2 = new JRadioButton("BT7");
        button2.addActionListener(rbListener);
        button2.setActionCommand("1");
        buttons.add(button2);
        group.add(button2);

        JRadioButton button3 = new JRadioButton("Tiger");
        button3.addActionListener(rbListener);
        button3.setActionCommand("2");
        buttons.add(button3);
        group.add(button3);

        panel.add(lButtons);
        panel.add(buttons);

        JButton button = new JButton("Start");
        panel.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseTanksMissions(chosen);
                startTheGame();
                loadingFrame.setVisible(false);
            }
        });

        return panel;
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

        for (Object el : bf.getTanks()) {
            AbstractTank tank = (AbstractTank) el;
            if (!tank.isDestroyed()) {
                tank.draw(g);
            }
        }

        bullet.draw(g);
    }
}
