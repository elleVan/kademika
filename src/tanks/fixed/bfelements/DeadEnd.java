package tanks.fixed.bfelements;

import tanks.fixed.AbstractBFElement;
import tanks.mobile.AbstractTank;

public class DeadEnd extends AbstractBFElement {

    private AbstractTank tank;

    public DeadEnd(int x, int y, AbstractTank tank) {
        super(x, y);
        this.tank = tank;
    }

    public AbstractTank getTank() {
        return tank;
    }
}
