package lessonsJD.lesson8;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SimpleListImpl<T extends String> implements SimpleList<T> {

    private List<T> list;

    private File file;

    private File tmp;

    public SimpleListImpl() {

        file = new File("src/lessonsJD/lesson8/list.txt");
        list = new ArrayList<>();

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try (
                    FileReader fr = new FileReader(file);
                    BufferedReader reader = new BufferedReader(fr)
            ) {
                String next;
                while ((next = reader.readLine()) != null) {
                    list.add((T) next);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void add(T str) {

        try (
                FileWriter fw = new FileWriter(file, true);
                BufferedWriter writer = new BufferedWriter(fw)
        ) {
            list.add(str);
            writer.write(str + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean contains(T str) {

        return list.contains(str);
    }

    public void remove(T str) {

        list.remove(str);

        try {
            tmp = new File("src/lessonsJD/lesson8/temp.txt");
            tmp.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (
                FileReader fr = new FileReader(file);
                BufferedReader reader = new BufferedReader(fr);

                FileWriter fw = new FileWriter(tmp);
                BufferedWriter writer = new BufferedWriter(fw)
        ) {

            String next;
            while ((next = reader.readLine()) != null) {
                if (!str.equals(next)) {
                    writer.write(next + "\n");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        file.delete();
        tmp.renameTo(file);
    }

    public int size() {

        return list.size();
    }

    public Iterator<T> iterator() {
        return list.iterator();
    }
}
