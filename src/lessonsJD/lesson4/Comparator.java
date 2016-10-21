package lessonsJD.lesson4;

public class Comparator implements java.util.Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        int length1 = o1.toString().length();
        int length2 = o2.toString().length();

        int count = findMin(length1, length2);

        for (int i = 0; i < count; i++) {
            String o1First = Character.toString(o1.toString().charAt(i)).toLowerCase();
            String o2First = Character.toString(o2.toString().charAt(i)).toLowerCase();

            int o1Index = alphabet.indexOf(o1First);
            int o2Index = alphabet.indexOf(o2First);

            int result = compareIndexes(o1Index, o2Index);

            if (result != 0) {
                return result;
            } else if (i == count - 1) {
                return compareIndexes(length1, length2);
            }
        }
        return 0;
    }

    public int compareIndexes(int index1, int index2) {
        if (index1 > index2) {
            return -1;
        } else if (index1 < index2) {
            return 1;
        }
        return 0;
    }

    public int findMin(int a, int b) {
        if (a > b) {
            return b;
        }
        return a;
    }
}