package lessonsJD.lesson8;

import java.util.Iterator;

public interface SimpleList<T> extends Iterable<T> {

    void add(T object);

    boolean contains(T object);

    void remove(T object);

    int size();

    Iterator<T> iterator();
}
