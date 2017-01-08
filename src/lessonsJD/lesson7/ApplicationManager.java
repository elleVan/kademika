package lessonsJD.lesson7;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApplicationManager {

    private List<Object> services = new ArrayList<>();
    public static final int LIMIT = 10;

    public ApplicationManager() {
    }

    public <T> T get(Class<T> clazz, Map<String, Object> params) throws Exception {

        T result = null;

        if (services.size() < LIMIT && clazz.getAnnotation(Service.class) != null) {
            result = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                Object value = params.get(field.getName());
                if (value != null) {
                    field.set(result, value);
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
