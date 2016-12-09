package tanks.fixed.bfelements;

import tanks.fixed.AbstractBFElement;
import tanks.helpers.Destroyable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Brick extends AbstractBFElement implements Destroyable{

    public Brick(int x, int y) {
        super(x, y);
        setColor(Color.blue);
        try {
            setImage(ImageIO.read(new File("brick.jpg")));
        } catch (IOException e) {
            System.err.println("Can't find image");
        }
    }

    @Override
    public void destroy() {
        setDestroyed(true);
    }
}
