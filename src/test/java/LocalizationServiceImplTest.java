import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {

    @Test
    void localeRussia() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String message = localizationService.locale(Country.RUSSIA);

        assertEquals("Добро пожаловать", message);
    }

    @Test
    void localeUSA() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String message = localizationService.locale(Country.USA);

        assertEquals("Welcome", message);
    }

    @Test
    void localeOtherCountry() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String message = localizationService.locale(Country.GERMANY);

        assertEquals("Welcome", message);
    }
}