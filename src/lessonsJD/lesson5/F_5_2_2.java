package lessonsJD.lesson5;

import javax.swing.*;
import java.awt.*;

public class F_5_2_2 {

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

                g.setFont(new Font("Cambria", Font.BOLD, 24));
                g.drawString("Hello World", 10, 30);
            }
        };

        f.getContentPane().add(p);
    }
}
