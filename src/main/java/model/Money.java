package model;

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
     Addiert den Betrag zweier Objekte, gibt den Wert in einem neuen model.Money Objekt zurück
     ! 4 Euro + 4 Euro = 8 Euro ! -> Kann man darstellen.
     * @param other ein model.Money Object
     * @return neues model.Money-Object
     */
    public Money add(Money other) {
        checkCurrencyMatch(other);
        return new Money(this.amount.add(other.amount), this.currency);
    }

    /**
     *  Subtrahiert den Betrag zweier Objekte, gibt den Wert in einem neuen model.Money Objekt zurück
     *  ! 4 Euro -2 Euro = 2 Euro -> Kann man darstellen
     * @param other ein model.Money Object
     * @return neues model.Money-Object
     */
    public Money subtract(Money other) {
        checkCurrencyMatch(other);
        return new Money(this.amount.subtract(other.amount), this.currency);
    }

    /**
     *  Multipliziert den Betrag eines model.Money-Objekt mit ein Skaklar
     * @param faktor ein BigDecimal Objekt
     * @return ein BigDecimal Objekt, kein model.Money Objekt
     * ! 2 Euro * 2 Euro = 4 Euro^2 -> Gibt es im Finanzkontext nicht.
     */
    public Money multiply(BigDecimal faktor) {
        return new Money(this.amount.multiply(faktor), this.currency);
    }

   /**
     *  Dividiert den Betrag eines model.Money-Objekt mit ein BigDecimal-Wert
     * @param divisor ein BigDecimal Objekt
     * @return ein BigDecimal Objekt, kein model.Money Objekt
     * ! Kein Objekt als Parameter, denn 6 Euro / 3 Euro = 2 Euro/Euro
     */
    public Money divide(BigDecimal divisor) {
        return new Money(this.amount.divide(divisor, RoundingMode.HALF_UP), this.currency);
    }

    /**
     *  Überschriebene Interface Methode
     * @param other vergleichendes Object.
     * @return 0 -> true, -1 false
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

