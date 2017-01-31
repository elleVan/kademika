package lessonsJD.lesson9;

import java.awt.*;

public class Ship {

    private int x = 20;
    private int y = 190;

    private Color color = Color.BLUE;

    public Ship() {
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, 30, 20);
    }

    public void updateX() {
        x++;
    }

    public int getX() {
        return x;
    }
}
