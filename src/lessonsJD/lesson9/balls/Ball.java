package lessonsJD.lesson9.balls;

import java.awt.*;

public class Ball implements Runnable {

    private int x;
    private int y;

    private Color color;
    private int speed;

    private boolean toLeft = false;

    public Ball(int x, int y, Color color, int speed) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.speed = speed;
    }

    public void run() {

        while (true) {
            if (x > Balls.SIZE - 46) {
                toLeft = true;
            } else if (x < 0) {
                toLeft = false;
            }

            if (toLeft) {
                x -= speed * 2;
            } else {
                x += speed * 2;
            }

            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, 30, 30);
    }
}
