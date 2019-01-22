import com.financial.services.DataFixerService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ApiUnitsTest {
    @Test
    public void testParseApiJsonNull() {
        assertEquals(null, DataFixerService.parseCurrentApiJson("False"));
    }

    @Test
    public void testConvertToEur() {
        assertEquals(1.0, DataFixerService.convertCurrencyToEur(1.0, "EUR"), 0.0);
    }

    @Test(expected = NullPointerException.class)
    public void testConvertToFalseCurrency() {
        DataFixerService.convertCurrencyToEur(1.0, "False");
    }

    @Test
    public void testConvertToLowercaseCurrency() {
        assertEquals(1.0, DataFixerService.convertCurrencyToEur(1.0, "eur"), 0.0);
    }
}
