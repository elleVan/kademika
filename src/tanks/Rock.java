package tanks;

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
