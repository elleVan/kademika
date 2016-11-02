package lessonsJD.lesson5;

import javax.swing.*;
import java.awt.*;

public class F_5_2_5 {

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setLocation(300, 100);
        f.setMinimumSize(new Dimension(800, 600));
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);

        JPanel p = new DrawingBoard();

        f.getContentPane().add(p);
    }
}
