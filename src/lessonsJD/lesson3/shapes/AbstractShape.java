package lessonsJD.lesson3.shapes;

import java.awt.*;

public abstract class AbstractShape implements Drawable {

    protected int lineWidth = 1;

    protected Color drawColor = Color.black;
    protected Color fillColor = Color.cyan;

    protected int x;
    protected int y;

    protected int width;
    protected int height;
}
