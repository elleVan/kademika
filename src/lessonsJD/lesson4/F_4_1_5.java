package lessonsJD.lesson4;

import java.util.ArrayList;
import java.util.List;

public class F_4_1_5 {

    public static void main(String[] args) {
        List<Integer> some = new ArrayList<>();
        some.add(3);
        some.add(4);
        some.contains(3);
        some.get(0);
        some.remove(0);
        some.set(0, 3);
        some.toArray();

        Classroom classroom = new Classroom();
        List<Student> newarr = classroom.getStudents();
        System.out.println(newarr.toString());
        Student student = new Student("Enn", "Tutu");
        classroom.enter(student);
        System.out.println(newarr.toString());
        System.out.println(classroom.isPresent("Enn", "Tutu"));


        System.out.println(student.equals(null));

    }
}
