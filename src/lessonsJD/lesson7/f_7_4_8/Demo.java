package lessonsJD.lesson7.f_7_4_8;

public class Demo {

    public static void main(String[] args) throws Exception {
        ApplicationManager am = new ApplicationManager();
        Sweet sweet = am.getService(Sweet.class);

        System.out.println(sweet.getName() + " - " + sweet.getQuantity() + " - " + sweet.getPrice() + " - "
                + sweet.getInStock() + " - " + sweet.getCategory());
    }
}
