package tanks.fixed;

import tanks.helpers.Drawable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class AbstractBFElement implements Drawable {

    private int x;
    private int y;

    private Color color;
    private boolean isDestroyed = false;

    private Image image;

    public AbstractBFElement(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics g) {

        if (!isDestroyed && image != null) {
            g.drawImage(image, this.getX(), this.getY(), new ImageObserver() {
                @Override
                public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                    return false;
                }
            });
        }
    }

    public void addImage() {
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
