package lessonsJD.lesson8;

import java.io.*;

public class F_8_3_4 {

    public static void main(String[] args) throws FileNotFoundException {

        File f = new File("src/lessonsJD/lesson8/test.txt");

        System.out.println("Console");

        System.setOut(new PrintStream(f));

        System.out.println("File");
    }
}
