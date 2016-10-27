package tanks.mobile;

import tanks.helpers.Action;
import tanks.helpers.Destroyable;
import tanks.helpers.Direction;
import tanks.helpers.Drawable;

public interface Tank extends Drawable, Destroyable {

    Action setUp();

    void turn(Direction direction);

    void move();

    Bullet fire();

    int getX();

    int getY();

    Direction getDirection();

    void updateX(int x);

    void updateY(int y);

    int getSpeed();

    int getMovePath();

}