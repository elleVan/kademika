package lessonsJD.lesson8;

import java.io.*;

public class F_8_2_6 {

    public static void main(String[] args) {

        String path = "src/lessonsJD/lesson8/F_8_2_6.java";
        path = path.replace("/", File.separator);

        File file = new File(path);
        copyFile(file);
    }

    public static void copyFile(File f) {

        String path = f.getAbsolutePath();
        int idx = path.lastIndexOf(".");

        String namePath = path.substring(0, idx);
        String ext = path.substring(idx);

        File newFile = new File(namePath + "Copy" + ext);

        try (
                FileInputStream in = new FileInputStream(f);
                FileOutputStream out = new FileOutputStream(newFile)
        ) {
            byte[] array = new byte[256];
            int i;
            while ((i = in.read(array)) != -1) {
                out.write(array, 0, i);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}