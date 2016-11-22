package lessonsJD.lesson5;

import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class F_5_3_4 {

    public static void main(String[] args) throws ParseException {
        Calendar calendar = new GregorianCalendar();
        calendar.set(1996, 0, 30);
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
    }
}
