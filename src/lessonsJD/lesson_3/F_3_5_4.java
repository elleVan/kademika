package lessonsJD.lesson_3;

public class F_3_5_4 {

    public static void main(String[] args) {
        try {
            throw new Exception();
        } catch (Exception e) {
            throw new IllegalArgumentException();
        } finally {
            System.out.println("I want to be executed");
        }
    }
}
