package tanks;

public abstract class AbstractTank {

    private int TANK_STEP = 1;
    private int RANDOM_STEP = 2;

    protected int speed = 10;

    private int x;
    private int y;

    private Direction direction;

    private ActionField af;
    private BattleField bf;

    public AbstractTank(ActionField af, BattleField bf) {
        this(af, bf, 320, 384, Direction.DOWN);
    }

    public AbstractTank(ActionField af, BattleField bf, int x, int y, Direction direction) {
        this.af = af;
        this.bf = bf;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void destroy() throws Exception {
        x = -100;
        y = -100;
        af.repaint();
        Thread.sleep(3000);
        af.newAggressor();
    }

    public void clean() throws Exception {

        String quadrant = af.getQuadrant(x, y);
        int x = Integer.parseInt(quadrant.split("_")[0]);
        int y = Integer.parseInt(quadrant.split("_")[1]);

        String directions = af.findDirections();

        int moveTo1 = Integer.parseInt(directions.split("_")[0]);
        int moveTo2 = Integer.parseInt(directions.split("_")[1]);
        String direction = directions.split("_")[2];

        smashingAround();

        if (direction.equals("horizontal move")) {

            moveToQuadrantSmash(moveTo1 + 1, y + 1);
            moveToQuadrantSmash(moveTo2 + 1, y + 1);

        } else if (direction.equals("vertical move")) {

            moveToQuadrantSmash(x + 1, moveTo1 + 1);
            moveToQuadrantSmash(x + 1, moveTo2 + 1);

        }

    }

    private void moveToQuadrantSmash(int hx, int vy) throws Exception {

        String coordinates = af.getQuadrantXY(hx, vy);
        int xDest = Integer.parseInt(coordinates.split("_")[0]);
        int yDest = Integer.parseInt(coordinates.split("_")[1]);

        smashingMoveHorizontal(xDest);
        smashingMoveVertical(yDest);
    }

    private void smashingMoveHorizontal(int xDest) throws Exception {

        if (x != xDest) {
            while (x < xDest) {
                smashingFireVertical();
                fireMove(Direction.RIGHT);
            }

            while (x > xDest) {
                smashingFireVertical();
                fireMove(Direction.LEFT);
            }

            smashingFireVertical();
        }
    }

    private void smashingMoveVertical(int yDest) throws Exception {

        if (y != yDest) {
            while (y < yDest) {
                smashingFireHorizontal();
                fireMove(Direction.DOWN);
            }

            while (y > yDest) {
                smashingFireHorizontal();
                fireMove(Direction.UP);
            }

            smashingFireHorizontal();
        }
    }

    private void smashingFireVertical() throws Exception {

        smashingWhatSee(Direction.UP);
        smashingWhatSee(Direction.DOWN);
    }

    private void smashingFireHorizontal() throws Exception {

        smashingWhatSee(Direction.LEFT);
        smashingWhatSee(Direction.RIGHT);
    }

    private void smashingAround() throws Exception {

        smashingWhatSee(Direction.UP);
        smashingWhatSee(Direction.DOWN);
        smashingWhatSee(Direction.LEFT);
        smashingWhatSee(Direction.RIGHT);
    }

    private void smashingWhatSee(Direction direction) throws Exception {

        String quadrant = af.getQuadrant(x, y);
        int x = Integer.parseInt(quadrant.split("_")[0]);
        int y = Integer.parseInt(quadrant.split("_")[1]);

        int shoot = 0;

        if (direction == Direction.UP) {
            for (int i = BattleField.Q_MIN; i <= y; i++) {
                if (!af.isCellArrayBFEmpty(x, i)) {
                    shoot++;
                }
            }
        } else if (direction == Direction.DOWN) {
            for (int i = y; i <= BattleField.Q_MAX; i++) {
                if (!af.isCellArrayBFEmpty(x, i)) {
                    shoot++;
                }
            }
        } else if (direction == Direction.LEFT) {
            for (int i = BattleField.Q_MIN; i <= x; i++) {
                if (!af.isCellArrayBFEmpty(i, y)) {
                    shoot++;
                }
            }
        } else {
            for (int i = x; i <= BattleField.Q_MAX; i++) {
                if (!af.isCellArrayBFEmpty(i, y)) {
                    shoot++;
                }
            }
        }

        fireInOneWay(direction, shoot);
    }

    private void fireInOneWay(Direction direction, int shoots) throws Exception {
        turn(direction);
        for (int i = 0; i < shoots; i++) {
            fire();
        }
    }

    public void moveRandom() throws Exception {

        int countMoveUp = 0,
                countMoveDown = 0,
                countMoveLeft = 0,
                countMoveRight = 0,
                maxMoveUp = 3,
                maxMoveDown = 3,
                maxMoveLeft = 3,
                maxMoveRight = 3;

        while (true) {
            String time = String.valueOf(System.currentTimeMillis());
            String maxAll;
            int direction = Integer.parseInt(time.substring(time.length() - 1));
            Direction dir = Direction.NONE;

            if ((direction == 1 || direction == 5) && countMoveUp < maxMoveUp) {

                dir = Direction.UP;
                fireMove(dir);
                countMoveUp++;

            } else if ((direction == 2 || direction == 6 || direction == 9) && countMoveDown < maxMoveDown) {

                dir = Direction.DOWN;
                fireMove(dir);
                countMoveDown++;

            } else if ((direction == 3 || direction == 7) && countMoveLeft < maxMoveLeft) {

                dir = Direction.LEFT;
                fireMove(dir);
                countMoveLeft++;

            } else if ((direction == 4 || direction == 8 || direction == 0) && countMoveRight < maxMoveRight) {

                dir = Direction.RIGHT;
                fireMove(dir);
                countMoveRight++;

            }

            maxAll = findMaxAll(dir, countMoveUp, countMoveDown, countMoveLeft, countMoveRight, maxMoveUp,
                    maxMoveDown, maxMoveLeft, maxMoveRight);

            if (!maxAll.equals("0")) {
                maxMoveUp = Integer.parseInt(maxAll.split("_")[0]);
                maxMoveDown = Integer.parseInt(maxAll.split("_")[1]);
                maxMoveLeft = Integer.parseInt(maxAll.split("_")[2]);
                maxMoveRight = Integer.parseInt(maxAll.split("_")[3]);
            }

        }

    }

    private String findMaxAll(Direction direction, int countUp, int countDown, int countLeft, int countRight,
                              int maxUp, int maxDown, int maxLeft, int maxRight) {

        if (countUp == maxUp && countDown == maxDown && countLeft == maxLeft && countRight == maxRight) {
            if (direction == Direction.UP) {

                maxLeft = countLeft + RANDOM_STEP * 2;
                maxUp = findMax(countUp, countDown, countRight) + RANDOM_STEP;
                maxDown = maxUp;
                maxRight = maxUp;

            } else if (direction == Direction.DOWN) {

                maxRight = countRight + RANDOM_STEP * 2;
                maxUp = findMax(countUp, countDown, countLeft) + RANDOM_STEP;
                maxDown = maxUp;
                maxLeft = maxUp;

            } else if (direction == Direction.LEFT) {

                maxUp = countUp + RANDOM_STEP * 2;
                maxDown = findMax(countRight, countDown, countLeft) + RANDOM_STEP;
                maxLeft = maxDown;
                maxRight = maxDown;

            } else if (direction == Direction.RIGHT) {

                maxDown = countDown + RANDOM_STEP * 2;
                maxUp = findMax(countRight, countUp, countLeft) + RANDOM_STEP;
                maxLeft = maxUp;
                maxRight = maxUp;

            }
            return String.valueOf(maxUp + "_" + maxDown + "_" + maxLeft + "_" + maxRight);
        }
        return "0";
    }

    private int findMax(int a, int b, int c) {

        if (a > b && a > c) {
            return a;
        } else if (b > c) {
            return b;
        }

        return c;
    }

    public void moveToQuadrant(int hx, int vy) throws Exception {

        String coordinates = af.getQuadrantXY(hx, vy);
        int xDest = Integer.parseInt(coordinates.split("_")[0]);
        int yDest = Integer.parseInt(coordinates.split("_")[1]);

        fireMoveHorizontal(xDest);
        fireMoveVertical(yDest);
    }

    private void fireMoveHorizontal(int xDest) throws Exception {

        if (x < xDest) {
            while (x != xDest) {
                fireMove(Direction.RIGHT);
            }
        } else {
            while (x != xDest) {
                fireMove(Direction.LEFT);
            }
        }
    }

    private void fireMoveVertical(int yDest) throws Exception {

        if (y < yDest) {
            while (y != yDest) {
                fireMove(Direction.DOWN);
            }
        } else {
            while (y != yDest) {
                fireMove(Direction.UP);
            }
        }
    }

    private void fireMove(Direction direction) throws Exception {
        while (af.isOccupied(direction)) {
            turn(direction);
            fire();
        }
        turn(direction);
        move();
    }

    public void turn(Direction direction) throws Exception {
        this.direction = direction;
        af.processTurn(this);
    }

    public void move() throws Exception {
        af.processMove(this);
    }

    public void fire() throws Exception {
        Bullet bullet = new Bullet((x + 25), (y + 25), direction);
        af.processFire(bullet);
    }

    public void updateX(int i) {
        x += i;
    }

    public void updateY(int i) {
        y += i;
    }

    public int getSpeed() {
        return speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getTANK_STEP() {
        return TANK_STEP;
    }
}
