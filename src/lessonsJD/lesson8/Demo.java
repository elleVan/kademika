package lessonsJD.lesson8;

public class Demo {

    public static void main(String[] args) {

        SimpleList list = new SimpleListImpl();
        String second = "second";
        list.add("first");
        list.add(second);
        list.add("third");
        list.remove(second);
        list.add("fourth");

        for (Object str : list) {
            System.out.println(str);
        }
    }
}
