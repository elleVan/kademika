package lessonsJD.lesson6.f_6_2_4;

public interface Subject {

    void addObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObserver();
}
