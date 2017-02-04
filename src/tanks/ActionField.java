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
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ActionField extends JPanel {

    private static final boolean COLORED_MODE = false;

    private Image imageBlank;

    private BattleField bf;
    private List<Bullet> bullets = new ArrayList<>();

    private static final int MODEL_T34 = 0;
    private static final int MODEL_BT7 = 1;
    private static final int MODEL_TIGER = 2;

    private Mission missionT34 = Mission.DEFENDER;
    private Mission missionBT7 = Mission.KILL_EAGLE;
    private Mission missionTiger = Mission.KILL_DEFENDER;

    private static final int defenderX = 256;
    private static final int defenderY = 384;

    private static final int killEagleX = 0;
    private static final int killEagleY = 64;

    private static final int killDefenderX = 512;
    private static final int killDefenderY = 64;

    private int chosen = 0;
    private String chosenLog;

    private JFrame loadingFrame;

    private boolean replay = false;
    private Object[] logs;

    public ActionField() {
        createLoadingFrame();
    }

    private void runTheGame(AbstractTank tank) {

        while (true) {
            if (!bf.getDefender().isDestroyed() && !tank.isDestroyed()) {
                if (tank.getMission() == Mission.KILL_EAGLE) {
                    tank.findPath(4, 8);
                    tank.setPathActions(tank.generatePathActions());
                } else if (tank.getMission() == Mission.KILL_DEFENDER) {
                    tank.findPath(bf.getDefender().getX() / BattleField.Q_SIZE, bf.getDefender().getY() / BattleField.Q_SIZE);
                    tank.setPathActions(tank.generatePathActions());
                } else if (tank.getMission() == Mission.DEFENDER) {
                    tank.getDefenderPath();
                    tank.setPathActions(tank.generatePathActions());
                }

                Action action = tank.setUp();
                processAction(action, tank);

            }
            if (tank.isDestroyed() || bf.getDefender().isDestroyed() || bf.getEagle().isDestroyed()) {
                break;
            }
        }
    }

    private void runTheReplay(AbstractTank tank) {
        try (
                FileReader fr = new FileReader("src/tanks/logs/" + chosenLog);
                BufferedReader br = new BufferedReader(fr)
        ) {

            String str;
            int i = 0;

            String tankCode;

            if (tank.getMission() == Mission.DEFENDER) {
                tankCode = "D";
            } else if (tank.getMission() == Mission.KILL_EAGLE) {
                tankCode = "E";
            } else {
                tankCode = "A";
            }

            while ((str = br.readLine()) != null) {
                if (!(str.contains("New") && i < 3)) {
                    String[] arr = str.split(" ");

                    if (arr.length == 1) {
                        String tankStr = str.substring(0, 1);
                        String actionStr = str.substring(1, 2);
                        String directionStr = str.substring(2);

                        Action action;
                        Direction direction;

                        if (!tankCode.equals(tankStr)) {
                            continue;
                        }

                        if ("D".equals(directionStr)) {
                            direction = Direction.DOWN;
                        } else if ("U".equals(directionStr)) {
                            direction = Direction.UP;
                        } else if ("R".equals(directionStr)) {
                            direction = Direction.RIGHT;
                        } else {
                            direction = Direction.LEFT;
                        }

                        if (!tank.getDirection().equals(direction)) {
                            tank.turn(direction);
                        }

                        if ("M".equals(actionStr)) {
                            action = Action.MOVE;
                        } else {
                            action = Action.FIRE;
                        }

                        processAction(action, tank);
                    }

                    if (str.contains("New") && tankCode.equals(arr[2])) {
                        if ("T34".equals(arr[1])) {
                            tank = newTank(MODEL_T34, tank.getMission());
                        } else if ("BT7".equals(arr[1])) {
                            tank = newTank(MODEL_BT7, tank.getMission());
                        } else {
                            tank = newTank(MODEL_TIGER, tank.getMission());
                        }
                    }

                }
                i++;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void startTheReplay() {

        try (
                FileReader fr = new FileReader("src/tanks/logs/" + chosenLog);
                BufferedReader br = new BufferedReader(fr)
        ) {

            String str;
            List<AbstractTank> tanks = new ArrayList<>();

            while ((str = br.readLine()) != null && str.contains("New")) {

                String[] arr = str.split(" ");

                Mission mission;
                if ("D".equals(arr[2])) {
                    mission = Mission.DEFENDER;
                } else if ("E".equals(arr[2])) {
                    mission = Mission.KILL_EAGLE;
                } else {
                    mission = Mission.KILL_DEFENDER;
                }

                if ("T34".equals(arr[1])) {
                    tanks.add(newTank(MODEL_T34, mission));
                } else if ("BT7".equals(arr[1])) {
                    tanks.add(newTank(MODEL_BT7, mission));
                } else {
                    tanks.add(newTank(MODEL_TIGER, mission));
                }

            }

            Thread defenderThread = null;

            for (AbstractTank tank : tanks) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runTheReplay(tank);
                    }
                });
                thread.start();
                if (tank.getMission() == Mission.DEFENDER) {
                    defenderThread = thread;
                }
            }

            try {
                defenderThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        CardLayout cardLayout = (CardLayout) loadingFrame.getContentPane().getLayout();
        cardLayout.next(loadingFrame.getContentPane());
    }

    private AbstractTank newTank(int model) {

        int x;
        int y;
        Mission mission;

        StringBuilder builder = new StringBuilder();
        builder.append("New ");

        if (model == MODEL_T34) {
            mission = missionT34;
            builder.append("T34 ");
        } else if (model == MODEL_BT7) {
            mission = missionBT7;
            builder.append("BT7 ");
        } else {
            mission = missionTiger;
            builder.append("Tiger ");
        }

        if (mission == Mission.DEFENDER) {
            x = defenderX;
            y = defenderY;
            builder.append("D");
        } else if (mission == Mission.KILL_EAGLE) {
            x = killEagleX;
            y = killEagleY;
            builder.append("E");
        } else {
            x = killDefenderX;
            y = killDefenderY;
            builder.append("A");
        }

        AbstractTank tank;
        if (model == MODEL_T34) {
            tank = new T34(bf, x, y, Direction.DOWN, mission);
        } else if (model == MODEL_BT7) {
            tank = new BT7(bf, x, y, Direction.DOWN, mission);
        } else {
            tank = new Tiger(bf, x, y, Direction.DOWN, mission);
        }

        bf.addTank(tank);

        if (mission == Mission.DEFENDER) {
            bf.setDefender(tank);
        } else if (mission == Mission.KILL_EAGLE) {
            bf.setKillEagle(tank);
        } else {
            bf.setKillDefender(tank);
        }

        try (
                FileWriter fw = new FileWriter("src/tanks/logs/log" + bf.getGameId() + ".txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter writer = new PrintWriter(bw)
        ) {
            writer.println(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tank;
    }

    private AbstractTank newTank(int model, Mission mission) {

        int x;
        int y;

        if (mission == Mission.DEFENDER) {
            x = defenderX;
            y = defenderY;
        } else if (mission == Mission.KILL_EAGLE) {
            x = killEagleX;
            y = killEagleY;
        } else {
            x = killDefenderX;
            y = killDefenderY;
        }

        AbstractTank tank;
        if (model == MODEL_T34) {
            tank = new T34(bf, x, y, Direction.DOWN, mission);
        } else if (model == MODEL_BT7) {
            tank = new BT7(bf, x, y, Direction.DOWN, mission);
        } else {
            tank = new Tiger(bf, x, y, Direction.DOWN, mission);
        }

        bf.addTank(tank);

        if (mission == Mission.DEFENDER) {
            bf.setDefender(tank);
        } else if (mission == Mission.KILL_EAGLE) {
            bf.setKillEagle(tank);
        } else {
            bf.setKillDefender(tank);
        }

        return tank;
    }

    private void processAction(Action a, AbstractTank t) {
        if (a == Action.MOVE) {
            processMove(t);
        } else if (a == Action.FIRE) {
            Bullet bullet = t.fire();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        processFire(bullet);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            try {
                Thread.sleep(t.getSpeed());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void processMove(AbstractTank tank) {

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

        while (covered < BattleField.Q_SIZE) {
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

            try {
                Thread.sleep(tank.getSpeed());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private void processFire(Bullet bullet) throws InterruptedException {

        bullets.add(bullet);

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

            if (processInterception(bullet)) {
                bullet.destroy();
                bullets.remove(bullet);
            }

            Thread.sleep(bullet.getSpeed());

            if (bullet.isDestroyed()) {
                break;
            }
        }
    }

    private boolean processInterception(Bullet bullet) throws InterruptedException {

        int x = bullet.getX() / BattleField.Q_SIZE;
        int y = bullet.getY() / BattleField.Q_SIZE;

        if (isQuadrantOnTheField(x, y)) {
            AbstractBFElement bfElement = bf.scanQuadrant(x, y);

            if (!bfElement.isDestroyed() && (bfElement instanceof Destroyable)) {
                if (!((bfElement instanceof Rock) && !(bullet.getTank() instanceof Tiger))) {
                    bf.destroyObject(x, y);
                    return true;
                }
                return true;
            }

            for (int i = 0; i < bf.getTanks().size(); i++) {
                AbstractTank tank = bf.getTanks().get(i);
                if (!tank.isDestroyed() && bullet.getTank() != tank &&
                        checkInterception(tank.getX(), tank.getY(), bullet.getX(), bullet.getY())) {
                    tank.destroy();
                    if (tank.isDestroyed() && tank.getMission() != Mission.DEFENDER) {
                        bf.removeTank(tank);
                        if (!replay) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }

                                    AbstractTank newTank;
                                    if (tank instanceof BT7) {
                                        newTank = newTank(MODEL_BT7);
                                    } else if (tank instanceof Tiger) {
                                        newTank = newTank(MODEL_TIGER);
                                    } else {
                                        newTank = newTank(MODEL_T34);
                                    }

                                    runTheGame(newTank);

                                }
                            }).start();
                        }
                    }
                    return true;
                }
            }
        }

        return false;
    }

    private boolean checkInterception(int x, int y, int bulletX, int bulletY) {

        int minX = x;
        int maxX = x + BattleField.Q_SIZE;
        int minY = y;
        int maxY = y + BattleField.Q_SIZE;

        if (x >= 0 && x < 576 && y >= 0 && y < 576) {
            if ((bulletX >= minX) && (bulletX <= maxX) && (bulletY >= minY) && (bulletY <= maxY)) {
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

    private void startTheGame() {

        bf = new BattleField();

        File log = new File("src/tanks/logs/log" + bf.getGameId() + ".txt");
        try {
            log.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        newTank(MODEL_T34);
        newTank(MODEL_BT7);
        newTank(MODEL_TIGER);

        try {
            imageBlank = ImageIO.read(new File("src/tanks/images/blank.jpg"));
        } catch (IOException e) {
            System.err.println("Can't find imageName");
        }
    }

    private JFrame createGameFrame() {

        Thread defenderThread = null;

        for (AbstractTank tank : bf.getTanks()) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    runTheGame(tank);
                }
            });
            thread.start();
            if (tank.getMission() == Mission.DEFENDER) {
                defenderThread = thread;
            }
        }

        try {
            defenderThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        CardLayout cardLayout = (CardLayout) loadingFrame.getContentPane().getLayout();
        cardLayout.next(loadingFrame.getContentPane());

        return loadingFrame;
    }

    private JFrame createLoadingFrame() {
        JFrame frame = new JFrame("BATTLE FIELD");
        frame.getContentPane().setLayout(new CardLayout());
        loadingFrame = frame;
        frame.setLocation(750, 150);
        frame.setMinimumSize(new Dimension(BattleField.Q_SIZE * (BattleField.Q_MAX + 1) + 16, BattleField.Q_SIZE * (BattleField.Q_MAX + 1) + 38));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(createLoadingPanel());
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    repaint();
                    try {
                        Thread.sleep(1000 / 60);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        return frame;
    }

    private JPanel createLoadingPanel() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

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

        logs = findLogs().toArray();
        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(logs));
        chosenLog = (String) logs[0];

        panel.add(lButtons, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(buttons, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        JButton button = new JButton("Start");
        panel.add(button, new GridBagConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseTanksMissions(chosen);
                startTheGame();

                replay = false;

                CardLayout cardLayout = (CardLayout) loadingFrame.getContentPane().getLayout();
                cardLayout.next(loadingFrame.getContentPane());
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        createGameFrame();
                    }
                });
                thread.start();
                logs = findLogs().toArray();
                comboBox.setModel(new DefaultComboBoxModel(logs));
            }
        });

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox box = (JComboBox) e.getSource();
                chosenLog = (String) box.getSelectedItem();
            }
        });
        panel.add(comboBox, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(20, 0, 0, 0), 0, 0));

        JButton buttonR = new JButton("Replay");
        panel.add(buttonR, new GridBagConstraints(2, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(20, 0, 0, 0), 0, 0));

        buttonR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                bf = new BattleField();

                try {
                    imageBlank = ImageIO.read(new File("src/tanks/images/blank.jpg"));
                } catch (IOException ex) {
                    System.err.println("Can't find imageName");
                }

                replay = true;

                CardLayout cardLayout = (CardLayout) loadingFrame.getContentPane().getLayout();
                cardLayout.next(loadingFrame.getContentPane());
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        startTheReplay();
                    }
                });
                thread.start();
            }
        });

        return panel;
    }

    private List<String> findLogs() {

        File file = new File("src/tanks/logs");
        List<String> result = new ArrayList<>();

        for (File f : file.listFiles()) {
            result.add(f.getName());
        }
        return result;
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

        for (Object el : bf.getWaters()) {
            AbstractBFElement water = (AbstractBFElement) el;
            water.draw(g);
        }

        for (int j = 0; j < bullets.size(); j++) {
            bullets.get(j).draw(g);
        }
    }
}
