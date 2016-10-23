package lessonsJD.lesson4;

import java.util.Iterator;

public class F_4_2_1 {

    public static void main(String[] args) {
        SimpleLinkedList list = new SimpleLinkedList();
        Student student = new Student("a", "d");

        list.addLast(new Student("b", "n"));
        list.addLast(new Student("f", "d"));
        list.addLast(student);
        list.printList();
        System.out.println();
        for (Object o : list) {
            if (o != null) {
                System.out.println(o.toString());
            }
        }

        System.out.println();
        for (Iterator<Object> it = list.iterator(); it.hasNext();) {
            it.next();
            it.next();
            it.remove();
            break;
        }

        list.printList();
    }
}
