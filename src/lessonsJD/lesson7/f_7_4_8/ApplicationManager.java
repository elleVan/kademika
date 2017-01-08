package lessonsJD.lesson7.f_7_4_8;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ApplicationManager {

    private List<Object> services = new ArrayList<>();
    public static final int LIMIT = 10;

    public ApplicationManager() {
    }

    public <T> T get(Class<T> clazz, List<Object> params) throws Exception {

        T result = null;

        if (services.size() < LIMIT && clazz.getAnnotation(Service.class) != null) {
            result = clazz.newInstance();
            Method[] methods = clazz.getDeclaredMethods();

            for (Method method : methods) {
                if (method.getAnnotation(initService.class) != null) {
                    method.invoke(result, params.toArray());
                    break;
                }
            }
            services.add(result);
        }

        return result;
    }

    public void remove(Object service) {
        services.remove(service);
    }

    public List<Object> getServices() {
        return new ArrayList<>(services);
    }
}
