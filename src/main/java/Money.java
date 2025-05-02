import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Objects;

public final class Money implements Comparable<Money> {

    private final BigDecimal amount;
    private final Currency currency;

    public Money(BigDecimal amount, Currency currency) {
        if (amount == null || currency == null) {
            throw new IllegalArgumentException("Amount and currency must not be null");
        }
        this.amount = amount;
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    private void checkCurrencyMatch(Money money) {
        if (!this.currency.equals( money.currency)) {
            throw new IllegalArgumentException("Currency mismatch: " +
                    this.currency + " vs. " +  money.currency);
        }
    }

    /**
      Hier übergeben wir ein Money-Object.
      4 Euro + 4 Euro = 8 Euro
     */
    public Money add(Money other) {
        checkCurrencyMatch(other);
        return new Money(this.amount.add(other.amount), this.currency);
    }

    public Money subtract(Money other) {
        checkCurrencyMatch(other);
        return new Money(this.amount.subtract(other.amount), this.currency);
    }

    /**
     * !! Parameter ist kein Money-Object!
     * Denn 4 euro * 4 Euro = 16 Euro^2, gibt es nicht
     * @param factor BigDecimal
     * @return Ergebnis in BigDecimal
     */
    public Money multiply(BigDecimal factor) {
        return new Money(this.amount.multiply(factor), this.currency);
    }

    /**
     * Kein Objekt als Parameter, denn 6 Euro / 3 Euro = 2 Euro/Euro
     * @return das Ergebnis der Division
     */
    public Money divide(BigDecimal divisor) {
        return new Money(this.amount.divide(divisor, RoundingMode.HALF_UP), this.currency);
    }

    /**
     *  Überschriebene Interface Methode
     * @param other the object to be compared.
     * @return
     */

    @Override
    public int compareTo(Money other) {
        checkCurrencyMatch(other); // wirft Exception, wenn ungleiche Währung
        return this.amount.compareTo(other.amount);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Money)) return false;
        Money other = (Money) obj;
        return amount.compareTo(other.amount) == 0 &&
                currency.equals(other.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount.stripTrailingZeros(), currency);
    }

    @Override
    public String toString() {
        return amount + " " + currency.getCurrencyCode();
    }
}

