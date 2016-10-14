package lessonsJD.lesson4;

public class Student {

    private String name;
    private String secondName;

    public Student(String name, String secondName) {
        this.name = name;
        this.secondName = secondName;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Student) {
            Student student = (Student) obj;
            if (student.getName().equals(name) && student.getSecondName().equals(secondName)) {
                return true;
            }
        }

        return false;
    }
}
