package lessonsJD.lesson5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class F_5_3_2 {

    public static void main(String[] args) {
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
        String dateStr = "30 Jan 1996";

        try {
            Date date = format.parse(dateStr);
            System.out.println(format.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
