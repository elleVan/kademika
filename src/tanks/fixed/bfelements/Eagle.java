package tanks.fixed.bfelements;

import tanks.fixed.AbstractBFElement;
import tanks.helpers.Destroyable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Eagle extends AbstractBFElement implements Destroyable{

    public Eagle(int x, int y) {
        super(x, y);
        setColor(Color.orange);
    }

    @Override
    public void destroy() {
        setDestroyed(true);
    }

    @Override
    public void addImage() {
        super.addImage();
        try {
            setImage(ImageIO.read(new File("eagle.png")));
        } catch (IOException e) {
            System.err.println("Can't find image");
        }
    }
}
