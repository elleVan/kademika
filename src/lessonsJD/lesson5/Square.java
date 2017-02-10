package lessonsJD.lesson5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class Square {

    private Color color = Color.BLUE;

    public Square() {
        JFrame frame = new JFrame();
        frame.setLocation(500, 150);
        frame.setMinimumSize(new Dimension(400, 350));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.getContentPane().add(createPanel());

        frame.pack();
        frame.setVisible(true);
    }

    private JPanel createPanel() {
        final JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(color);
                g.fillRect(100, 100, 100, 100);
            }
        };

        panel.setPreferredSize(new Dimension(100, 100));
        panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Random random = new Random();
                int r = random.nextInt(256);
                int g = random.nextInt(256);
                int b = random.nextInt(256);
                color = new Color(r, g, b);
                panel.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        return panel;
    }

}
