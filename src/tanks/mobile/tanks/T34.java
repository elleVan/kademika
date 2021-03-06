package tanks.mobile.tanks;

import tanks.helpers.Direction;
import tanks.fixed.BattleField;
import tanks.helpers.Mission;
import tanks.mobile.AbstractTank;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class T34 extends AbstractTank {

    public T34(BattleField bf, int x, int y, Direction direction, Mission mission) {
        super(bf, x, y, direction, mission);
        colorTank = new Color(0, 210, 0);
        colorTower = new Color(255, 220, 0);
    }

    public Image[] createImages() {
        Image[] array = new Image[4];
        try {
            array[0] = ImageIO.read(new File("tank-U.png"));
            array[1] = ImageIO.read(new File("tank-D.png"));
            array[2] = ImageIO.read(new File("tank-L.png"));
            array[3] = ImageIO.read(new File("tank-R.png"));
        } catch (IOException e) {
            System.err.println("Can't find imageName");
        }
        return array;
    }
}
