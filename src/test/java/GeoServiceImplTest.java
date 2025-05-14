import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {

    @Test
    void byIpRussianSegment() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location location = geoService.byIp("172.123.12.19");

        assertEquals(Country.RUSSIA, location.getCountry());
    }

    @Test
    void byIpAmericanSegment() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location location = geoService.byIp("96.44.183.149");

        assertEquals(Country.USA, location.getCountry());
    }

    @Test
    void byIpOtherSegment() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location location = geoService.byIp("11.22.33.44");

        assertNull(location);
    }

    @Test
    void byIpLocalhost() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location location = geoService.byIp("127.0.0.1");

        assertNull(location.getCountry());
    }
}