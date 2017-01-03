package lessonsJD.lesson7.f_7_3_6;

public class ServiceImpl implements Service {

    private boolean initiated;

    public ServiceImpl() {
    }

    @Override
    public void init() {
        initiated = true;
    }

    public boolean isInitiated() {
        return initiated;
    }
}
