package tanks.mobile.tanks;

import tanks.helpers.Action;
import tanks.helpers.Direction;
import tanks.fixed.BattleField;
import tanks.mobile.AbstractTank;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BT7 extends AbstractTank {

    public BT7(BattleField bf) {
        super(bf);
        speed = super.getSpeed() / 2;
    }

    public BT7(BattleField bf, int x, int y, Direction direction) {
        super(bf, x, y, direction);
        speed = super.getSpeed() / 2;
    }

    @Override
    public Action setUp() {
        if (step >= getPathAll().size()) {
            step = 0;
        }
        for (Object el : turnToEnemy(detectEnemy())) {
            turn((Direction) el);
            return Action.FIRE;
        }

        while (!(getPathAll().get(step) instanceof Action)) {
            turn((Direction) getPathAll().get(step++));
        }
        if (step >= getPathAll().size()) {
            step = 0;
        }
        return (Action) getPathAll().get(step++);
    }

    public Image[] createImages() {
        Image[] array = new Image[4];
        try {
            array[0] = ImageIO.read(new File("tankBlue-U.png"));
            array[1] = ImageIO.read(new File("tankBlue-D.png"));
            array[2] = ImageIO.read(new File("tankBlue-L.png"));
            array[3] = ImageIO.read(new File("tankBlue-R.png"));
        } catch (IOException e) {
            System.err.println("Can't find imageName");
        }
        return array;
    }

    @Override
    public void addImages() {
        setImages(createImages());
    }
}
