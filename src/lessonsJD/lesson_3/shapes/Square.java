package lessonsJD.lesson_3.shapes;

import java.awt.*;

public class Square extends Rectangle {

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(4));
        g.setColor(Color.yellow);
        g.fillRect(400, 190, 70, 70);
        g.setColor(Color.blue);
        g.drawRect(400, 190, 70, 70);
    }
}
