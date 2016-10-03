package tanks.fixed.bfelements;

import tanks.ActionField;
import tanks.helpers.Destroyable;
import tanks.fixed.AbstractBFElement;

import java.awt.*;

public class Rock extends AbstractBFElement implements Destroyable {

    public Rock(ActionField af) {
        super(af);
        setCode("R");
        setColor(Color.gray);
    }

    @Override
    public void destroy() {
        setCode("");
    }
}
