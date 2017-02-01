package lessonsJD.lesson9.skating;

import java.util.ArrayList;
import java.util.List;

public class AnotherSkatingRink implements SkatingRink {

    private List<Skates> skatesList;

    public AnotherSkatingRink() {
        skatesList = new ArrayList<>();

        skatesList.add(new Skates());
        skatesList.add(new Skates());
    }

    @Override
    public Skates getSkates(Skater skater) {

        Skates skates = skatesList.remove(0);

        System.out.println(skater.getName() + " got skates");

        return skates;
    }

    @Override
    public void returnSkates(Skates skates, Skater skater) {

        skatesList.add(skates);
        System.out.println(skater.getName() + " returned skates");
    }

    public boolean isAvailableSkates() {
        return (!skatesList.isEmpty());
    }
}
