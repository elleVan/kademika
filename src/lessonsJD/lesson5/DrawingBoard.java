package lessonsJD.lesson5;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class DrawingBoard extends JPanel {

    private Image image;

    public DrawingBoard() {
        try {
            image = ImageIO.read(new File("tank.png"));
        } catch (IOException e) {
            System.err.println("Can't find image");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(image, 100, 100, new ImageObserver() {
            @Override
            public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                return false;
            }
        });
    }
}
