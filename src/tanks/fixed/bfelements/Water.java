package tanks.fixed.bfelements;

import tanks.fixed.AbstractBFElement;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class Water extends AbstractBFElement {

    public Water(int x, int y) {
        super(x, y);
        setColor(Color.cyan);
        addImage();
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Composite before = g2.getComposite();
        Composite trans = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f);
        g2.setComposite(trans);
        g2.drawImage(getImage(), this.getX(), this.getY(), new ImageObserver() {
            @Override
            public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                return false;
            }
        });
        g2.setComposite(before);
    }

    @Override
    public void addImage() {
        try {
            setImage(ImageIO.read(new File("water.jpg")));
        } catch (IOException e) {
            System.err.println("Can't find image");
        }
    }
}
