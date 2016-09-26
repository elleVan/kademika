package lessonsJD.lesson_3.shapes;

import java.awt.*;

public class Circle extends Shape {

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.green);
        g.fillOval(100, 60, 100, 100);
        g.setColor(Color.red);
        g.drawOval(100, 60, 100, 100);
    }
}
