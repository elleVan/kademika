package lessonsJD.lesson_3;

public class F_3_5_2 {

    public static void main(String[] args) {
        func();
    }

    public static void func() {
        try {
            return;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("I want to be executed");
        }
    }
}
