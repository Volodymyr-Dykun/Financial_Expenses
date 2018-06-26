import com.financial.ApiUtils;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by Volodymyr Dykun on 25.06.2018.
 */
public class ApiUnitsTest {
    @Test
    public void testParseApiJsonNull(){
        assertEquals(null,ApiUtils.parseCurrentApiJson("False"));
    }
    @Test
    public void testConvertToEur(){
        assertEquals(1.0,ApiUtils.convertCurrencyToEur(1.0,"EUR"),0.0);
    }
    @Test(expected = NullPointerException.class)
    public void testConvertToFalseCurrency(){
        ApiUtils.convertCurrencyToEur(1.0,"False");
    }
    @Test
    public void testConvertToLowercaseCurrency(){
        assertEquals(1.0,ApiUtils.convertCurrencyToEur(1.0,"eur"),0.0);
    }
}
