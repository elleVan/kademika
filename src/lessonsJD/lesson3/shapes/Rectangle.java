package lessonsJD.lesson3.shapes;

import java.awt.*;

public class Rectangle extends AbstractShape {

    public Rectangle() {
        x = 250;
        y = 90;
        width = 100;
        height = 90;

        lineWidth = 5;
        drawColor = Color.blue;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(lineWidth));
        g.setColor(fillColor);
        g.fillRect(x, y, width, height);
        g.setColor(drawColor);
        g.drawRect(x, y, width, height);
    }
}
