import model.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class MoneyTest {

    private Money money_1;
    private Money money_2;
    private Money money_3;
    private Money money_4;

    @BeforeEach
    void init() {
        money_1 = new Money(BigDecimal.valueOf(54), Currency.getInstance("EUR"));
        money_2 = new Money(BigDecimal.valueOf(8185), Currency.getInstance("EUR"));
        money_3 = new Money(BigDecimal.valueOf(891), Currency.getInstance("USD"));
        money_4 = new Money(BigDecimal.valueOf(891), Currency.getInstance("USD"));
    }

    @Test
    void shouldReturnNegativeWhenNoTEqual() {
        assertEquals(-1, Integer.signum(money_1.compareTo(money_2)));
    }

    @Test
    void shouldReturnZeroWhenEqual() {
        assertEquals(0, money_1.compareTo(money_1));
        assertEquals(0, money_3.compareTo(money_4));
    }

    @ParameterizedTest
    @MethodSource("criticalNumbers")
    void multiplyWithCriticalNumbers(BigDecimal factor) {
        //Arrange
        Money result = money_1.multiply(factor);
        // Erwarteter Betrag durch BigDecimal.multiply()
        assertEquals(money_1.getAmount().multiply(factor), result.getAmount());
    }

    @Test
    void shouldThrowsExceptionWhenCurrencyMismatch() {
        assertThrowsExactly(IllegalArgumentException.class, () -> money_1.compareTo(money_4));
    }

    @ParameterizedTest
    @MethodSource("criticalNumbers")
    void addWithCriticalNumbers(BigDecimal factor) {
        Money moneyTest = new Money(factor, Currency.getInstance("EUR"));
        Money result = money_1.add(moneyTest);

        // Erwarteter Betrag durch BigDecimal.add()
        assertEquals(money_1.getAmount().add(factor), result.getAmount());

    }

    @ParameterizedTest
    @MethodSource("defaultNumbers")
    void addWithDefaultNumbers(BigDecimal a) {
        Money moneyTest = new Money(a, Currency.getInstance("EUR"));
         //BigDecimal Addtion
        Money result = money_1.add(moneyTest);
        assertEquals(money_1.getAmount().add(moneyTest.getAmount()),result.getAmount());
    }



    @Test
    void shouldThrowOnCurrencyMismatchInArithmeticOperation() {

        assertThrowsExactly(IllegalArgumentException.class, () -> money_1.add(money_4));
        assertThrowsExactly(IllegalArgumentException.class, () -> money_1.subtract(money_3));

    }


    //Für Addition und Subtraktion
    static Stream<BigDecimal> defaultNumbers(){
        return Stream.of(
                BigDecimal.valueOf(-4141),
                BigDecimal.valueOf(9149194),
                BigDecimal.valueOf(21),
                BigDecimal.ZERO
        );
    }

    //Kritische Zahlen für Division und Multiplikation
    static Stream<BigDecimal> criticalNumbers() {
        return Stream.of(
                BigDecimal.valueOf(0.0019),
                BigDecimal.valueOf(-0.0019),
                BigDecimal.valueOf(-1),
                BigDecimal.valueOf(0),
                BigDecimal.valueOf(0.01),
                BigDecimal.valueOf(-0.01),
                BigDecimal.ONE
        );
    }
}
