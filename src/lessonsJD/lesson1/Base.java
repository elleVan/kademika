package lessonsJD.lesson1;

/**
 * Created by Mary on 16.09.2016.
 */
import java.util.Arrays;

public class Base {

    public static void main(String[] args) {



//		f3_5();
//		processInterception();
//		vstr5();
//		f3_1_array();
//		vstr4();
//		f2_2_6string();
//		f2_2_1();
//		square(2.5);
//		square(2);
//		f1_5_8();
//		f1_4_4string();
//		f1_3_3();
//		f1_2_4();
//		f1_1_13();
//		f1();
    }

    static void f3_5() {
        int[] src = {1, 2, 3, 4, 5};
        int[] dest = new int[10];

        System.arraycopy(src, 1, dest, 2, 3);
        System.out.println(Arrays.toString(dest));
    }

//	boolean processInterception() {
//		String quadrant = getQuadrant(bulletX, bulletY);
//		int y = Integer.parseInt(quadrant.split("_")[0]);
//		int x = Integer.parseInt(quadrant.split("_")[1]);
//
//		if (y >= 0 && y < 9 && x >= 0 && x < 9) {
//			if (!battleField[y][x].trim().isEmpty()) {
//				battleField[y][x] = " ";
//				return true;
//			}
//		}
//
//		return false;
//
//	}

    static void vstr5() {
        int[] data = {1, 2, 3, 4, 5};
        for (int el : data) {
            el = 3;
            System.out.println(el);
        }
        System.out.println(Arrays.toString(data));
    }

    static void f3_1_array() {

        int[] data1 = new int[] {4, 5, 6};
        int[] data2 = {4, 5, 6};
        int[] data3 = {};
        int[] data4 = null;
//		int[] data5 = new int[Integer.MAX_VALUE];
        String[] names = new String[10];
        boolean[] data6 = new boolean[5];

        System.out.println(new int[] {14, 25, 46});
        System.out.println(data3);
        System.out.println(Arrays.toString(data3));
        System.out.println(Arrays.toString(data1) + Arrays.toString(data2));
        System.out.println(data4);
        names[1] = "Milk";
        System.out.println(names[6]);
        System.out.println(data6[3]);


    }

    static void vstr4() {
        double value = 1.5;
        aaa:	while(true) {
            System.out.println(value);

            while (value < 0) {
                if (value < -2.7) {
                    break aaa;
                }

                value -= 0.2;
            }
            System.out.println("Exit");
            value -= 0.3;
        }
        System.out.println("We");

    }

    static void f2_2_6string() {
        String a = "a";
        String b = "b";
        String c = "a";
        String d = new String("a");
        String e = d;
        String f = new String(e);

        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(e == a);
        System.out.println(a.equals(d));
        System.out.println(e == f);
        System.out.println(a == f);
        System.out.println(f);
    }

    static void f2_2_1() {
        String str = null;
        System.out.println(str);
    }

    static double square(double number) {
        System.out.print("double ");
        return(number * number);
    }

    static double square(int number) {
        System.out.print("int ");
        return(number * number);
    }

    static void f1_5_8() {
        double value = 3.14;
        System.out.println(String.valueOf(value));

        String str = "543.12";
        value = Double.valueOf(str) + 10;
        System.out.println(Double.valueOf(value));
    }

    static void f1_4_4string() {

        String mtQuote = "Let us always meet each other with smile, for the smile is the beginning of love.";
        System.out.println(mtQuote.length());
        System.out.println(mtQuote.charAt(5));
        System.out.println(mtQuote.substring(5, 11));
        System.out.println(mtQuote.indexOf("m"));
        System.out.println(mtQuote.replace("smile", ":)")); //не меняет изначальную строку
        System.out.print("\n \t");
        System.out.println(mtQuote);
        System.out.println("\n \t".charAt(2));

//		a != A
    }

    static void f1_3_3() {
        int sum = 2, sum2 = 2;
        double cv = 3.1, cv2 = 3.09;

        sum += 10 * cv; //sum = sum + 10 * cv
        System.out.println(sum);
        sum2 += 10 * cv2;
        System.out.println(sum2);
    }

    static void f1_2_4() {
        System.out.println(Double.MAX_VALUE);
        System.out.println(Double.MIN_VALUE);
        System.out.println(Long.MAX_VALUE);
    }

    static void f1_1_13() {
        System.out.println(System.currentTimeMillis());
        System.out.println(System.nanoTime());
    }

    static void f1() {

        int a = 11; // 11 - целый литерал

        double d1 = 10.0, d2 = 10d;
        long l = 10L;
        System.out.println(d1 + d2);

        a = (int)100_000_000_000.0 / 2;
        System.out.println(a);

        double d = 3.9;

        l = (long)d + l;
        System.out.println(l);

        l = 100 * 100_000_000; // 100 - int, 100_000_000 - int, (100 * 100_000_000) - int, переполнение
        System.out.println(l);
    }

}
