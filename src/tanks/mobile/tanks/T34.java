package tanks.mobile.tanks;

import tanks.ActionField;
import tanks.helpers.Action;
import tanks.helpers.Direction;
import tanks.fixed.BattleField;
import tanks.mobile.AbstractTank;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class T34 extends AbstractTank {

    public T34(BattleField bf) {
        super(bf);
        colorTank = new Color(0, 210, 0);
        colorTower = new Color(255, 220, 0);
        setImages(createImages());
    }

    public T34(BattleField bf, int x, int y, Direction direction) {
        super(bf, x, y, direction);
        colorTank = new Color(0, 210, 0);
        colorTower = new Color(255, 220, 0);
        setImages(createImages());
    }

    private Object[] actions = new Object[] {
            Direction.RIGHT,
            Action.FIRE,
    };

    private int step = 0;

    @Override
    public Action setUp() {
        if (step >= actions.length) {
            step = 0;
        }
        if (!(actions[step] instanceof Action)) {
            turn((Direction) actions[step++]);
        }
        if (step >= actions.length) {
            step = 0;
        }
        return (Action) actions[step++];
    }

    public Image[] createImages() {
        Image[] array = new Image[5];
        try {
            array[1] = ImageIO.read(new File("tank-U.png"));
            array[2] = ImageIO.read(new File("tank-D.png"));
            array[3] = ImageIO.read(new File("tank-L.png"));
            array[4] = ImageIO.read(new File("tank-R.png"));
        } catch (IOException e) {
            System.err.println("Can't find imageName");
        }
        return array;
    }
}
