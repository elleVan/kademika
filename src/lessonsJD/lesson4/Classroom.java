package lessonsJD.lesson4;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Classroom {

    private List<Student> students;

    public Classroom() {
        students = new LinkedList<>();
        initList();
    }

    public void initList() {
        Student[] array = new Student[] {
                new Student("Anton", "Lyzhnikov"),
                new Student("Vasya", "Boyarsky"),
                new Student("Liza", "Mechnikova"),
                new Student("Anna", "Boleyn"),
                new Student("Nika", "Vasylevsky")
        };

        Collections.addAll(students, array);
    }

    public void enter(Student student) {
        if (!students.contains(student)) {
            students.add(student);
        }
    }

    public void leave(Student student) {
        students.remove(student);
    }

    public int getStudentCount() {
        return students.toArray().length;
    }

    public boolean isPresent(String name, String secondName) {
        for (Student student : students) {
            if (student.getName().equals(name) && student.getSecondName().equals(secondName)) {
                return true;
            }
        }
        return false;
    }

    public void printStudentInfo() {
        System.out.println("STUDENTS:");
        for (Student student : students) {
            System.out.println(student.getName() + " " + student.getSecondName());
        }
        System.out.println();
    }

    public List<Student> getStudents() {
        return students;
    }
}
