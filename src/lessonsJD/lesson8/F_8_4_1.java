package lessonsJD.lesson8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class F_8_4_1 {

    public static void main(String[] args) throws Exception {

        URL url = new URL("https://www.google.com.ua/");
        URLConnection connection = url.openConnection();

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String str;
        while ((str = in.readLine()) != null) {
            System.out.println(str);
        }

        in.close();
    }
}
