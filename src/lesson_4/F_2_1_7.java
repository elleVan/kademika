package lesson_4;

public class F_2_1_7 {

    public static void main(String[] args) {

        BT7 bt7 = new BT7(TankColor.DARK_BLUE, 3);
        System.out.println(bt7.toString());
        bt7.move();

        T34 t34 = new T34(TankColor.DARK_GREEN, 4);
        System.out.println(t34.toString());
        t34.move();

        Tiger tiger = new Tiger(TankColor.BLACK, 2);
        System.out.println(tiger.toString());
        tiger.move();
    }
}
