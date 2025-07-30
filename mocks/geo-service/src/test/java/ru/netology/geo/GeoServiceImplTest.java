package ru.netology.geo;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {

    @Test
    void byIp_shouldReturnRussianLocationForRussianIp() {
        // Arrange
        GeoService geoService = new GeoServiceImpl();
        String russianIp = "172.123.12.19";

        // Act
        Location result = geoService.byIp(russianIp);

        // Assert
        assertNotNull(result);
        assertEquals(Country.RUSSIA, result.getCountry());
        assertEquals("Moscow", result.getCity());
    }

    @Test
    void byIp_shouldReturnAmericanLocationForAmericanIp() {
        // Arrange
        GeoService geoService = new GeoServiceImpl();
        String americanIp = "96.44.183.149";

        // Act
        Location result = geoService.byIp(americanIp);

        // Assert
        assertNotNull(result);
        assertEquals(Country.USA, result.getCountry());
        assertEquals("New York", result.getCity());
    }

    @Test
    void byIp_shouldReturnNullForLocalhost() {
        // Arrange
        GeoService geoService = new GeoServiceImpl();
        String localhostIp = "127.0.0.1";

        // Act
        Location result = geoService.byIp(localhostIp);

        // Assert
        assertNotNull(result);
        assertNull(result.getCountry());
        assertNull(result.getCity());
    }

    @Test
    void byIp_shouldReturnNullForUnknownIp() {
        // Arrange
        GeoService geoService = new GeoServiceImpl();
        String unknownIp = "10.0.0.1";

        // Act
        Location result = geoService.byIp(unknownIp);

        // Assert
        assertNull(result);
    }
}