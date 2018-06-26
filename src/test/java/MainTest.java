import com.financial.Expense;
import com.financial.Main;
import org.junit.Test;

import java.time.format.DateTimeParseException;

import static org.junit.Assert.assertEquals;

/**
 * Created by Volodymyr Dykun on 25.06.2018.
 */
public class MainTest {
    @Test(expected = DateTimeParseException.class)
    public void testAddFalseDate() {
        Main main = new Main();
        Expense expense = new Expense("2015-02-29", 21d, "USD", "expense");
        main.add(expense);
    }

    @Test (expected = NullPointerException.class)
    public void testAddFalseCurrency() {
        Main main = new Main();
        Expense expense = new Expense("2015-12-30", 21d, "False", "expense");
        main.add(expense);
    }

    @Test
    public void testAddLongName() {
        Expense expense = new Expense("2015-12-30", 21d, "USD", "expense #1 \"Milk\"");
        assertEquals("expense #1 \"Milk\"",expense.getName());
    }
}
