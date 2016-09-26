package lessonsJD.lesson_2;

public class Musician {

    public void play(Instrument i) {

        if (i instanceof Flute) {
            ((Flute) i).test();
        }
        i.sound();
        System.out.println(i.getClass());
    }
}
