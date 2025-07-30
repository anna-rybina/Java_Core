package ru.netology.i18n;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {

    @Test
    void testLocaleForRussia() {
        LocalizationService localizationService = new LocalizationServiceImpl();
        String message = localizationService.locale(Country.RUSSIA);

        assertEquals("Добро пожаловать", message);
    }

    @Test
    void testLocaleForUSA() {
        LocalizationService localizationService = new LocalizationServiceImpl();
        String message = localizationService.locale(Country.USA);

        assertEquals("Welcome", message);
    }
}