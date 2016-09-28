package lessonsJD.lesson3.shapes;

import java.awt.*;

public class Triangle extends AbstractShape {

    public Triangle() {
        fillColor = Color.green;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(lineWidth));
        int[] coordinatesX = {200, 300, 150};
        int[] coordinatesY = {200, 300, 250};
        g.setColor(fillColor);
        g.fillPolygon(coordinatesX, coordinatesY, 3);
        g.setColor(drawColor);
        g.drawPolygon(coordinatesX, coordinatesY, 3);
    }
}
