package lessonsJD.lesson8;

import java.io.File;
import java.io.IOException;

public class F_8_2_1 {

    public static void main(String[] args) {

        createFile();
        printDir();
    }

    public static void createFile() {
        String path = "src/lessonsJD/New/New.java";
        path = path.replace("/", File.separator);

        File file = new File(path);
        file.getParentFile().mkdirs();

        try {
            file.createNewFile();
        } catch (IOException e) {

        }
    }

    public static void printDir() {
        File file = new File(System.getProperty("user.dir"));

        for (File f : file.listFiles()) {
            if (f.isDirectory()) {
                System.out.println(f.getName());
            }
        }
    }
}
