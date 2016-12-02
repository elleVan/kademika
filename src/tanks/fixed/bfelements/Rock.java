package tanks.fixed.bfelements;

import tanks.fixed.AbstractBFElement;
import tanks.helpers.Destroyable;

import java.awt.*;

public class Rock extends AbstractBFElement implements Destroyable {

    public Rock(int x, int y) {
        super(x, y);
        setColor(Color.gray);
        setImageName("rock.jpg");
    }

    @Override
    public void destroy() {
        setDestroyed(true);
    }
}
