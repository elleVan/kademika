package lessonsJD.lesson3;

public class F_3_1_4 {

    public static void main(String[] args) {
        try {
            doSmth();
        } catch (InterruptedException e) {
            System.out.println("Do smth with e");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doSmth() throws InterruptedException {

        Thread.currentThread().interrupt();
        Thread.sleep(1000);
    }
}
