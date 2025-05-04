package CustomAssertions;

import org.assertj.core.api.AbstractAssert;
import java.math.BigDecimal;
import java.util.Currency;
import model.Money;

public class MoneyAssert extends AbstractAssert<MoneyAssert, Money> {

    public MoneyAssert(Money actual) {
        super(actual, MoneyAssert.class);
    }

    /**
     * Konstruktor fürs Method-Channing
     *
     */
    public static MoneyAssert assertThat(Money actual) {
        return new MoneyAssert(actual);
    }

    public MoneyAssert hasAmountOf(String expected) {
        isNotNull();
        BigDecimal expectedAmount = new BigDecimal(expected);
        if (!actual.getAmount().equals(expectedAmount)) {
            failWithMessage("Expected amount to be <%s> but was <%s>", expectedAmount, actual.getAmount());
        }
        return this;
    }

    public MoneyAssert equalCurrency(Money obj) {
        isNotNull();
        Currency currency = obj.getCurrency();
        if (!actual.getCurrency().equals(currency)) {
            failWithMessage("Expected currency to be <%s> but was <%s>", currency, actual.getCurrency());
        }
        return this;
    }

    public MoneyAssert add (Money obj) {
        isNotNull();
        BigDecimal expectedAmount = obj.getAmount().add(actual.getAmount());
        Money actualObj = actual.add(obj);
        BigDecimal actualAmount = actualObj.getAmount();

        if (!actualAmount.equals(expectedAmount)) {
            failWithMessage("Expected amount to be <%s> but was <%s>", expectedAmount, actualAmount);
        }

        return this;
    }

    public MoneyAssert isPositive() {
        isNotNull();
        if (actual.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            failWithMessage("Expected amount to be positive, but was <%s>", actual.getAmount());
        }
        return this;
    }

    public MoneyAssert isZero() {
        isNotNull();
        if (actual.getAmount().compareTo(BigDecimal.ZERO) != 0) {
            failWithMessage("Expected amount to be zero, but was <%s>", actual.getAmount());
        }
        return this;
    }
}
