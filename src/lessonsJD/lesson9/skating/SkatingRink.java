package lessonsJD.lesson9.skating;

public interface SkatingRink {

    Skates getSkates(Skater skater);

    void returnSkates(Skates skates, Skater skater);
}
