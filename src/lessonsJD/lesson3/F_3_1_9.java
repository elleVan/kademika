package lessonsJD.lesson3;

public class F_3_1_9 {

    public static void main(String[] args) {
        try {
            doSmth();
        } catch (MyPersonalException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Print this anyway");
        }
    }

    public static void doSmth() throws MyPersonalException {
        throw new MyPersonalException("MyPersonalException was caught");
    }
}
