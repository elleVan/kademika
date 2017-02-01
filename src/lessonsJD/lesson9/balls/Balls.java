package lessonsJD.lesson9.balls;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Balls extends JPanel {

    public static void main(String[] args) {

        new Balls();
    }

    public static final int SIZE = 550;

    private List<Ball> balls;

    private Color[] colors = new Color[] {
            Color.RED,
            Color.ORANGE,
            Color.YELLOW,
            Color.GREEN,
            Color.CYAN,
            Color.BLUE,
            Color.MAGENTA
    };

    public Balls() {
        balls = new ArrayList<>();
        createWindow();
        createBalls();

        while (true) {
            repaint();

            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private void createBalls() {

        int y = 0;
        for (int i = 0; i < 7; i++) {
            Ball ball = new Ball(0, y, colors[i], i + 1);
            balls.add(ball);
            new Thread(ball).start();
            y += 70;
        }
    }

    private void createWindow() {
        JFrame frame = new JFrame("BALLS");
        frame.setLocation(750, 150);
        frame.setMinimumSize(new Dimension(SIZE, SIZE));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Ball ball : balls) {
            ball.draw(g);
        }
    }
}
