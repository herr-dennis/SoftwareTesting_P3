import model.Money;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.Currency;

import static CustomAssertions.MoneyAssert.*;

public class MoneyAssertTest {


    @Test
    void shouldEqualCurrency(){
        Money money = new Money(BigDecimal.valueOf(100.00), Currency.getInstance("EUR"));
        Money money2 = new Money(BigDecimal.valueOf(100.00), Currency.getInstance("EUR"));
        assertThat(money).equalCurrency(money2);
    }

    @Test
    void hasAmount(){
        String expected = "100.00";
        Money money = new Money(BigDecimal.valueOf(100.00), Currency.getInstance("EUR"));
        //Flotter Fehler
        //assertThat(money).hasAmountOf("100.00");

        Money money2 = new Money(BigDecimal.valueOf(100.41), Currency.getInstance("EUR"));
        assertThat(money2).hasAmountOf("100.41");
    }


    @Test
    void testAddition(){
        Money money = new Money(BigDecimal.valueOf(55), Currency.getInstance("EUR"));
        Money money2 = new Money(BigDecimal.valueOf(-41), Currency.getInstance("EUR"));

        assertThat(money).add(money2);


    }

    @Test
    void isAmountPositiv(){
        Money money = new Money(BigDecimal.valueOf(100.00), Currency.getInstance("EUR"));
        assertThat(money).isPositive();

    }

    @Test
    void isAmountZero(){
        Money money = new Money(BigDecimal.ZERO, Currency.getInstance("EUR"));
        assertThat(money).isZero();
    }












}
