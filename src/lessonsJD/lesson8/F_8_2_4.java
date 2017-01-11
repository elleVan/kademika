package lessonsJD.lesson8;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class F_8_2_4 {

    public static void main(String[] args) {
        String path = "src/lessonsJD/lesson8/F_8_2_4.java";
        path = path.replace("/", File.separator);

        File f = new File(path);

        try (InputStream in = new FileInputStream(f)) {
            printStreamData(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printStreamData(InputStream in) {

        try {
            int i;
            while ((i = in.read()) != -1) {
                System.out.print((char) i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}