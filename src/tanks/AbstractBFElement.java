package tanks;

import java.awt.*;

public class AbstractBFElement implements Drawable {

    private String code;
    private Color color;

    private ActionField af;
    private BattleField bf;

    public AbstractBFElement(ActionField af) {
        this.af = af;
        this.bf = af.getBf();
    }

    @Override
    public void draw(Graphics g) {
        for (int j = 0; j < bf.getDimensionY(); j++) {
            for (int k = 0; k < bf.getDimensionX(); k++) {
                if (bf.scanQuadrant(k, j).equals(code)) {
                    String coordinates = af.getQuadrantXY(j + 1, k + 1);
                    int separator = coordinates.indexOf("_");
                    int y = Integer.parseInt(coordinates.substring(0, separator));
                    int x = Integer.parseInt(coordinates.substring(separator + 1));
                    g.setColor(color);
                    g.fillRect(x, y, 64, 64);
                }
            }
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
