package lessonsJD.lesson_3.shapes;

import java.awt.*;

public class Triangle extends Shape {

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(5));
        int[] coordinatesX = {200, 300, 150};
        int[] coordinatesY = {200, 300, 250};
        g.setColor(Color.red);
        g.fillPolygon(coordinatesX, coordinatesY, 3);
        g.setColor(Color.black);
        g.drawPolygon(coordinatesX, coordinatesY, 3);
    }
}
