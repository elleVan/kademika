package lessonsJD.lesson4;

public class Person {

    private String name;
    private int age;
    private long salary;

    public Person(String name, int age, long salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public boolean equals(Object obj) {
        return this.hashCode() == obj.hashCode();
    }

    @Override
    public int hashCode() {
        int result = 34;

        result = result * 37 + name.hashCode();
        result = result * 37 + new Integer(age).hashCode();
        result = result * 37 + new Long(salary).hashCode();

        return result;
    }
}
