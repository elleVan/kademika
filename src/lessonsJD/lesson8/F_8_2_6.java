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

        StringBuilder builder = new StringBuilder();

        try (FileInputStream in = new FileInputStream(f)) {
            int i;
            while ((i = in.read()) != -1) {
                builder.append((char) i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String path = f.getAbsolutePath();
        int idx = path.lastIndexOf(".");

        String namePath = path.substring(0, idx);
        String ext = path.substring(idx);

        File newFile = new File(namePath + "Copy" + ext);

        try (
                FileOutputStream fileOut = new FileOutputStream(newFile);
                BufferedOutputStream out = new BufferedOutputStream(fileOut, 256)
        ) {
            out.write(builder.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}