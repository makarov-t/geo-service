import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageSenderImplTest {

    @Mock
    private GeoService geoService;

    @Mock
    private LocalizationService localizationService;

    @InjectMocks
    private MessageSenderImpl messageSender;

    @Test
    void sendRussianTextForRussianIp() {
        // Arrange
        String russianIp = "172.123.12.19";
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, russianIp);

        when(geoService.byIp(russianIp))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        // Act
        String result = messageSender.send(headers);

        // Assert
        assertEquals("Добро пожаловать", result);
        verify(geoService, times(1)).byIp(russianIp);
        verify(localizationService, atLeastOnce()).locale(Country.RUSSIA);
    }

    @Test
    void sendEnglishTextForAmericanIp() {
        // Arrange
        String americanIp = "96.44.183.149";
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, americanIp);

        when(geoService.byIp(americanIp))
                .thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));
        when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        // Act
        String result = messageSender.send(headers);

        // Assert
        assertEquals("Welcome", result);
        verify(geoService, times(1)).byIp(americanIp);
        verify(localizationService, atLeastOnce()).locale(Country.USA);
    }

    @Test
    void sendEnglishTextForOtherIp() {
        // Arrange
        String otherIp = "11.22.33.44";
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, otherIp);

        when(geoService.byIp(otherIp))
                .thenReturn(new Location(null, null, null, 0));
        when(localizationService.locale(null))
                .thenReturn("Welcome");

        // Act
        String result = messageSender.send(headers);

        // Assert
        assertEquals("Welcome", result);
    }


}