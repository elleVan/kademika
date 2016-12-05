package lessonsJD.lesson5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class Circle {

    private Color color = Color.BLUE;
    private int x = 100;
    private int y = 100;
    private int width = 100;
    private int height = 100;

    public Circle() {
        JFrame frame = new JFrame();
        frame.setLocation(500, 150);
        frame.setMinimumSize(new Dimension(450, 500));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.getContentPane().add(createPanel());

        frame.pack();
        frame.setVisible(true);
    }

    private JPanel createPanel() {

        JPanel all = new JPanel();
        all.setLayout(new BoxLayout(all, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Click the ball ;)");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        all.add(label);

        JPanel medium = new JPanel();

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(color);
                g.fillOval(0, 0, width, height);
            }
        };

        panel.setPreferredSize(new Dimension(width, height));
        panel.setLocation(x, y);

        panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

                if (panel.contains(e.getPoint())) {
                    Random random = new Random();
                    int x2 = x;
                    int y2 = y;
                    while ((x2 >= x - width && x2 < x + width) || (y2 >= y - height && y2 < y + height) || y2 < 20 ) {
                        x2 = random.nextInt(300);
                        y2 = random.nextInt(300);
                    }
                    x = x2;
                    y = y2;

                    panel.setLocation(x, y);
                    panel.repaint();
                }

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        medium.add(panel);
        all.add(medium);

        return all;
    }
}