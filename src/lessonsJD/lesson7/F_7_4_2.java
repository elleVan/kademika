package lessonsJD.lesson7;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class F_7_4_2 {

    public static void main(String[] args) {
        printClassInfo(F_7_4_2.class);
        printClassMethods(F_7_4_2.class);
        printClassFields(Field.class);
    }

    public static void printClassInfo(Class someClass) {
        System.out.println(someClass.getName());
    }

    public static void printClassMethods(Class someClass) {
        for (Method el : someClass.getMethods()) {
            System.out.println(el.getName());
        }
    }

    public static void printClassFields(Class someClass) {
        for (Field el : someClass.getFields()) {
            System.out.println(el.getName());
        }
    }
}
