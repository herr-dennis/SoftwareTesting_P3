import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.plaf.BorderUIResource;
import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class MoneyTest {

    private Money money_1;
    private Money money_2;
    private Money money_3;

    @BeforeEach
    void init(){
        money_1 = new Money(BigDecimal.valueOf(54), Currency.getInstance("EUR"));
        money_2 = new Money(BigDecimal.valueOf(8185), Currency.getInstance("EUR"));
        money_3 = new Money(BigDecimal.valueOf(891),Currency.getInstance("USD"));

    }

    @Test
    void shouldFalseWhenNotEqual(){


    }

}
