package lessonsJD.lesson4;

import java.util.Iterator;

public class SimpleArrayList implements Iterable<Object> {

    private Object[] array;
    private int size;

    public SimpleArrayList() {
        array = new Object[5];
        size = 0;
    }

    public void addFirst(Object obj) {
        int length = array.length;
        if (size == length) {
            extendArray();
        }
        Object[] newArray = new Object[length];
        System.arraycopy(array, 0, newArray, 1, size);
        array = newArray;
        array[0] = obj;
        size++;
    }

    public void addLast(Object obj) {
        if (size == array.length) {
            extendArray();
        }
        array[size] = obj;
        size++;
    }

    public void addAfter(Object obj, Object prev) {
        int idx = findElement(prev);
        if (idx == -1) {
            throw new IllegalStateException("Element is missing");
        }
        int length = array.length;
        if (size == length) {
            extendArray();
        }
        Object[] newArray = new Object[length - idx - 1];
        System.arraycopy(array, idx + 1, newArray, 0, size - idx - 1);
        array[idx + 1] = obj;
        System.arraycopy(newArray, 0, array, idx + 2, size - idx - 1);
        size++;
    }

    public void printList() {
        for (int i = 0; i < size; i++) {
            System.out.println(array[i]);
        }
    }

    public int getSize() {
        return size;
    }

    public Iterator<Object> iterator() {
        return new SALIterator();
    }

    public void extendArray() {
        int length = array.length;
        Object[] newArray = new Object[length + length / 4 + 1];
        System.arraycopy(array, 0, newArray, 0, length);
        array = newArray;
    }

    public int findElement(Object obj) {
        for (int i = 0; i < size; i++) {
            if (array[i] == obj) {
                return i;
            }
        }
        return -1;
    }

    private class SALIterator implements Iterator<Object> {

        int current;

        @Override
        public boolean hasNext() {
            return (current != size);
        }

        @Override
        public Object next() {
            if (current < size) {
                Object result = array[current];
                current++;
                return result;
            }
            throw new IllegalStateException();
        }

        @Override
        public void remove() {
            int length = array.length;
            Object[] newArray = new Object[length - current - 1];
            System.arraycopy(array, current + 1, newArray, 0, size - current - 1);
            System.arraycopy(newArray, 0, array, current, size - current - 1);
            size--;
        }
    }
}
