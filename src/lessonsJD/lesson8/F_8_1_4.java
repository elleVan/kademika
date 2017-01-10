package lessonsJD.lesson8;

import java.io.ByteArrayInputStream;

public class F_8_1_4 {

    public static void main(String[] args) {
        byte[] bytes = {3, 4, 5, 34, -45, -63, 127};
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);

        printStreamData(in);
    }

    public static void printStreamData(ByteArrayInputStream in) {

        int i;
        while ((i = in.read()) != -1) {
            System.out.println((byte) i);
        }
    }
}
