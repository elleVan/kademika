package tanks;

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
