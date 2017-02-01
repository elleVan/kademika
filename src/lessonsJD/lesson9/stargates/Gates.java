package lessonsJD.lesson9.stargates;

import java.awt.*;

public class Gates {

    public static final int OPEN = 230;
    public static final int CLOSE = 150;

    private int x = 300;
    private int y = CLOSE;

    private boolean isOpen = false;

    public Gates() {
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, 100, 20, 70);
        g.fillRect(x, 230, 20, 70);
        g.setColor(Color.RED);
        g.fillRect(x + 20, y, 10, 100);
    }

    public void updateY(int sign) {
        y += sign;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
