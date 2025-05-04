
import model.InsuranceCalculator;
import model.InsuranceRequest;
import model.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InsuranceCalculatorTest {

    InsuranceCalculator insuranceCalculator;
    InsuranceRequest valid_1;
    InsuranceRequest valid_2;
    InsuranceRequest valid_3;


    @BeforeEach
    void init(){
        insuranceCalculator = new InsuranceCalculator();
        valid_1 = new InsuranceRequest(1, 35, 500, false, true);   // Basis, jung, Standard-Selbstbeteiligung, Nichtraucher, Neukunde
        valid_2 = new InsuranceRequest(2, 61, 1000, true, false);  // Komfort, älter, höchste Selbstbeteiligung, Raucher, Bestandskunde
        valid_3 = new InsuranceRequest(3, 45, 0, false, true);     // Premium, mittleres Alter, keine SB, Nichtraucher, Neukunde

    }


    @Test
    void testCalculatePremiumInEuro() {
        InsuranceCalculator calculator = new InsuranceCalculator(); // default: EUR
        InsuranceRequest request = new InsuranceRequest(1, 30, 500, false, false);

        Money result = calculator.calculatePremium(request);

        assertEquals(Currency.getInstance("EUR"), result.getCurrency());
        // Erwartung: 200 (Tarif) * 0.9 (Selbstbeteiligung) = 180
        assertEquals(0, new BigDecimal("180.00").compareTo(result.getAmount()));
    }

    @Test
    void testCalculatePremiumInDollar() {
        InsuranceCalculator calculator = new InsuranceCalculator(true); // Dollar aktiviert
        InsuranceRequest request = new InsuranceRequest(1, 30, 500, false, false);

        Money result = calculator.calculatePremium(request);

        assertEquals(Currency.getInstance("USD"), result.getCurrency());
        // Erwartung: 200 * 0.9 * 1.13 = 203.40
        assertEquals(0, new BigDecimal("203.40").compareTo(result.getAmount()));
    }

    @Test
    void testCalculatePremiumWithAllFactors() {
        InsuranceCalculator calculator = new InsuranceCalculator(); // EUR
        InsuranceRequest request = new InsuranceRequest(3, 65, 500, true, true); // Premium, alt, Raucher, Neukunde

        Money result = calculator.calculatePremium(request);

        // 400 + 50 = 450 * 1.4 * 1.05 * 0.9 = 595.35
        assertEquals(0, new BigDecimal("595.35").compareTo(result.getAmount()));
    }


    @ParameterizedTest
    @CsvFileSource(resources = "premium_cases.csv", numLinesToSkip = 1)
    void testPremiumBerechnung(
            int tarifklasse,
            int alter,
            int selbstbeteiligung,
            boolean raucher,
            boolean neukunde,
            boolean dollar,
            double erwarteterBetrag,
            String waehrung
    ) {
        InsuranceCalculator calculator = new InsuranceCalculator(dollar);
        InsuranceRequest request = new InsuranceRequest(tarifklasse, alter, selbstbeteiligung, raucher, neukunde);

        Money result = calculator.calculatePremium(request);

        assertEquals(Currency.getInstance(waehrung), result.getCurrency());
        assertEquals(0, BigDecimal.valueOf(erwarteterBetrag)
                .compareTo(result.getAmount().stripTrailingZeros()));

    }



    static Stream<Integer> invalidAge() {
        return Stream.of(
                -999,
                -1,     // zu niedrig
                0,      // untere Grenze, evtl. ungültig
                1200    // deutlich zu hoch
        );
    }



}
