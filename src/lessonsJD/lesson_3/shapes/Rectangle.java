package lessonsJD.lesson_3.shapes;

import java.awt.*;

public class Rectangle extends Shape {

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(4));
        g.setColor(Color.yellow);
        g.fillRect(250, 90, 100, 90);
        g.setColor(Color.blue);
        g.drawRect(250, 90, 100, 90);
    }
}
