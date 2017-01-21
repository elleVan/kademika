package lessonsJD.lesson8;

import java.io.*;

public class F_8_3_3 {

    public static void main(String[] args) {
        String path = "src/lessonsJD/lesson8/test.txt";
        path = path.replace("/", File.separator);
        File file = new File(path);
        changeEncoding(file, "UTF-8", "ASCII");
    }

    public static void changeEncoding(File f, String currentEncoding, String neededEncoding) {

        StringBuilder builder = new StringBuilder();
        try (
                FileInputStream in = new FileInputStream(f);
                InputStreamReader reader = new InputStreamReader(in, currentEncoding);
                BufferedReader bufferedReader = new BufferedReader(reader)
        ) {
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                builder.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (
                FileOutputStream out = new FileOutputStream(f);
                OutputStreamWriter writer = new OutputStreamWriter(out, neededEncoding);
                BufferedWriter bufferedWriter = new BufferedWriter(writer)
        ) {
            bufferedWriter.write(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}