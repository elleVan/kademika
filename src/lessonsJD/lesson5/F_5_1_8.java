package lessonsJD.lesson5;

import javax.swing.*;
import java.awt.*;

public class F_5_1_8 {

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setLocation(300, 100);
        f.setMinimumSize(new Dimension(800, 600));
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);

        JPanel p = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.setColor(Color.green);
                g.fillRect(0, 0, 800, 600);
            }
        };

        f.getContentPane().add(p);
    }
}
