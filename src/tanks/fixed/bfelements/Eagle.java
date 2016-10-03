package tanks.fixed.bfelements;

import tanks.ActionField;
import tanks.helpers.Destroyable;
import tanks.fixed.AbstractBFElement;

import java.awt.*;

public class Eagle extends AbstractBFElement implements Destroyable {

    public Eagle(ActionField af) {
        super(af);
        setCode("E");
        setColor(Color.orange);
    }

    @Override
    public void destroy() {
        setCode("");
    }
}
