package tanks.fixed.bfelements;

import tanks.fixed.AbstractBFElement;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Water extends AbstractBFElement {

    public Water(int x, int y) {
        super(x, y);
        setColor(Color.cyan);
        try {
            setImage(ImageIO.read(new File("water.jpg")));
        } catch (IOException e) {
            System.err.println("Can't find image");
        }
    }
}
