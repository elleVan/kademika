package tanks.fixed.bfelements;

import tanks.fixed.AbstractBFElement;
import tanks.helpers.Destroyable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Rock extends AbstractBFElement implements Destroyable {

    public Rock(int x, int y) {
        super(x, y);
        setColor(Color.gray);
        addImage();
    }

    @Override
    public void destroy() {
        setDestroyed(true);
    }

    @Override
    public void addImage() {
        try {
            setImage(ImageIO.read(new File("rock.jpg")));
        } catch (IOException e) {
            System.err.println("Can't find image");
        }
    }
}
