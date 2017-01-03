package lessonsJD.lesson7.f_7_3_6;

import java.util.ArrayList;
import java.util.List;

public class ServiceRepository {

    private List<Service> services = new ArrayList<>();
    public static final int LIMIT = 10;

    public ServiceRepository() {
    }

    public Service get() {

        Service result = null;

        if (services.size() < LIMIT) {
            result = new ServiceImpl();
            result.init();
            services.add(result);
        }

        return result;
    }

    public void remove(Service service) {
        services.remove(service);
    }

    public List<Service> getServices() {
        return new ArrayList<>(services);
    }
}
