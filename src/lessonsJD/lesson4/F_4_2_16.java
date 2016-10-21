package lessonsJD.lesson4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class F_4_2_16 {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("airplane");
        list.add("apricot");
        list.add("phone");
        list.add("radio");
        list.add("physics");
        list.add("sausage");
        list.add("book");
        list.add("bookshelf");
        list.add("butterfly");
        list.add("fox");

        Collections.sort(list, new Comparator());

        System.out.println(Arrays.toString(list.toArray()));
    }
}
