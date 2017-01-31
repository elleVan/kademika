package lessonsJD.lesson9;

import javax.swing.*;
import java.awt.*;

public class StarGate extends JPanel {

    public static void main(String[] args) {
        new StarGate();
    }

    private Ship ship;
    private Gates gates;

    public StarGate() {
        JFrame frame = new JFrame("STARGATE");
        frame.setLocation(750, 150);
        frame.setMinimumSize(new Dimension(500, 500));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);

        ship = new Ship();
        gates = new Gates();

        turnOnGates();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    moveShip();
                }
            }
        }).start();

        while (true) {
            repaint();
            sleep(17);
        }

    }

    private void moveShip() {

        if (ship.getX() < 450) {
            if (!gates.isOpen() && isShipInRange()) {
                try {
                    synchronized (gates) {
                        gates.notify();
                    }
                    synchronized (ship) {
                        ship.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (gates.isOpen() && !isShipInRange()) {
                synchronized (gates) {
                    gates.notify();
                }
            }

            ship.updateX();
            sleep(10);
        } else {
            ship.setX(20);
        }

    }

    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean isShipInRange() {
        return ship.getX() > (gates.getX() - 50) && ship.getX() < (gates.getX() + 50);
    }

    private void turnOnGates() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    try {
                        synchronized (gates) {
                            gates.wait();
                        }
                        while (!gates.isOpen()) {
                            openGates();
                        }
                        synchronized (ship) {
                            ship.notify();
                        }
                        synchronized (gates) {
                            gates.wait();
                        }
                        while (gates.isOpen()) {
                            closeGates();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void openGates() {
        if (!gates.isOpen()) {
            if (gates.getY() < Gates.OPEN) {
                gates.updateY(1);
            }

            if (gates.getY() == Gates.OPEN) {
                gates.setOpen(true);

            } else {
                sleep(15);
            }
        }
    }

    private void closeGates() {
        if (gates.isOpen()) {
            if (gates.getY() > Gates.CLOSE) {
                gates.updateY(-1);
            }

            if (gates.getY() == Gates.CLOSE) {
                gates.setOpen(false);

            } else {
                sleep(15);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ship.draw(g);
        gates.draw(g);
    }
}
