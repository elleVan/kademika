package lessonsJD.lesson_3.shapes;

import java.awt.*;

public class Triangle extends Shape {

    @Override
    public void draw(Graphics g) {
        int[] coordinatesX = {200, 300, 150};
        int[] coordinatesY = {200, 300, 250};
        g.setColor(Color.red);
        g.fillPolygon(coordinatesX, coordinatesY, 3);
        g.setColor(Color.black);
        g.drawPolygon(coordinatesX, coordinatesY, 3);
    }
}
