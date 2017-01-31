package tanks.mobile.tanks;

import tanks.helpers.Direction;
import tanks.fixed.BattleField;
import tanks.helpers.Mission;
import tanks.mobile.AbstractTank;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Tiger extends AbstractTank {

    private int armor;

    public Tiger(BattleField bf, int x, int y, Direction direction, Mission mission) {
        super(bf, x, y, direction, mission);
        armor = 1;
    }

    @Override
    public void destroy() {
        if (armor > 0) {
            armor--;
        } else {
            super.destroy();
        }

    }

    public Image[] createImages() {
        Image[] array = new Image[4];
        try {
            array[0] = ImageIO.read(new File("src/tanks/images/tankRed-U.png"));
            array[1] = ImageIO.read(new File("src/tanks/images/tankRed-D.png"));
            array[2] = ImageIO.read(new File("src/tanks/images/tankRed-L.png"));
            array[3] = ImageIO.read(new File("src/tanks/images/tankRed-R.png"));
        } catch (IOException e) {
            System.err.println("Can't find imageName");
        }
        return array;
    }
}
