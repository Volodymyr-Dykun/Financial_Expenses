import com.financial.services.ApiService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ApiUnitsTest {
    @Test
    public void testParseApiJsonNull() {
        assertEquals(null, ApiService.parseCurrentApiJson("False"));
    }

    @Test
    public void testConvertToEur() {
        assertEquals(1.0, ApiService.convertCurrencyToEur(1.0, "EUR"), 0.0);
    }

    @Test(expected = NullPointerException.class)
    public void testConvertToFalseCurrency() {
        ApiService.convertCurrencyToEur(1.0, "False");
    }

    @Test
    public void testConvertToLowercaseCurrency() {
        assertEquals(1.0, ApiService.convertCurrencyToEur(1.0, "eur"), 0.0);
    }
}
