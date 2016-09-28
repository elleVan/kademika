package lessonsJD.lesson3.shapes;

import java.awt.*;

public class Circle extends AbstractShape {

    public Circle() {
        x = 100;
        y = 60;
        width = 100;
        height = width;

        lineWidth = 3;
        drawColor = Color.red;
        fillColor = Color.yellow;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(lineWidth));
        g.setColor(fillColor);
        g.fillOval(x, y, width, height);
        g.setColor(drawColor);
        g.drawOval(x, y, width, height);
    }
}
