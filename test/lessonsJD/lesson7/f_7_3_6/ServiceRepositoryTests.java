package test.lessonsJD.lesson7.f_7_3_6;

import static org.junit.Assert.*;

import lessonsJD.lesson7.f_7_3_6.Service;
import lessonsJD.lesson7.f_7_3_6.ServiceRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ServiceRepositoryTests {

    private ServiceRepository sr;

    @Before
    public void init() {
        sr = new ServiceRepository();
    }

    @Test
    public void testNewServiceWasInitiated() {

        Service service = sr.get();
        assertTrue(service.isInitiated());
    }

    @Test
    public void testNewServiceWasPutInList() {

        Service service = sr.get();
        assertTrue(sr.getServices().contains(service));
    }

    @Test
    public void testGetWhenLimitReached() {

        while (sr.getServices().size() < ServiceRepository.LIMIT) {
            sr.get();
        }
        assertEquals(null, sr.get());
    }

    @Test
    public void testRemove() {

        Service service = sr.get();
        sr.remove(service);
        assertFalse(sr.getServices().contains(service));
    }
}
