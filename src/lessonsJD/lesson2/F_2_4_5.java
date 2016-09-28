package lessonsJD.lesson2;

public class F_2_4_5 {

    public static void main(String[] args) {
        System.out.println(darkZone(100, 1, 1_000_000));
    }

    public static int darkZone(int monthlyPayment, int interest, int dreamSum) {
        int deposit = 50000;
        int months = 0;
        while (deposit < dreamSum) {
            deposit += monthlyPayment;
            deposit += (deposit * interest / 1200);
            months++;
            monthlyPayment += monthlyPayment / 45;
            if (months % 12 == 0) {
                System.out.println(monthlyPayment);
            }

        }
        return months;
    }
}
