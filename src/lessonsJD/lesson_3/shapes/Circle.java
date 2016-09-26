package lessonsJD.lesson_3.shapes;

import java.awt.*;

public class Circle extends Shape {

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3));
        g.setColor(Color.green);
        g.fillOval(100, 60, 100, 100);
        g.setColor(Color.red);
        g.drawOval(100, 60, 100, 100);
    }
}
