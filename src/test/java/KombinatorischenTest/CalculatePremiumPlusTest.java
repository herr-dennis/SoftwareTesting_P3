package KombinatorischenTest;

import model.InsuranceCalculator;
import model.InsuranceRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatePremiumPlusTest {


    private InsuranceCalculator calculatePremium;

    // Neue Fälle für PREMIUM+ (Tarif = 4, nur Neukunden)
    private InsuranceRequest t7, t8;
    private static final BigDecimal T7_RESULT = new BigDecimal("472.50");  // 450 × 1.05
    private static final BigDecimal T8_RESULT = new BigDecimal("647.85");  // (450×1.4×0.9 + 50)×1.05

    @BeforeEach
    void setUp() {
        calculatePremium = new InsuranceCalculator();


        t7 = new InsuranceRequest(4, 1,   0,    false, true );
        t8 = new InsuranceRequest(4, 120, 500,  true,  true );
    }

    @Test
    void testPremiumPlusWithoutSurchargeOrDeductible() {
        // PREMIUM+ ohne Alterssurcharge, ohne Selbstbeteiligung, kein Raucher, Neukunde
        BigDecimal result = calculatePremium.calculatePremium(t7).getAmount().stripTrailingZeros();
        assertEquals(T7_RESULT.stripTrailingZeros(), result,
                "PREMIUM+ Grundtarif für Neukunde ohne Zuschläge passt nicht");
    }

    @Test
    void testPremiumPlusWithAgeDeductibleAndSmoking() {
        // PREMIUM+ mit Alter > 60, 500€ Selbstbeteiligung, Raucher, Neukunde
        BigDecimal result = calculatePremium.calculatePremium(t8).getAmount().stripTrailingZeros();
        assertEquals(T8_RESULT.stripTrailingZeros(), result,
                "PREMIUM+ mit Zuschlägen für Neukunde passt nicht");
    }

    @Test
    void testPremiumPlusNotAllowedForExistingCustomer() {
        // PREMIUM+ darf nur für Neukunden sein → für Bestandskunden Fehler
        InsuranceRequest invalid = new InsuranceRequest(4, 30, 0, false, false);
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> calculatePremium.calculatePremium(invalid));
        assertTrue(ex.getMessage().contains("PREMIUM+ ist nur für Neukunden"),
                "Erwartete Fehlermeldung für Bestandskunde bei PREMIUM+ fehlt");
    }

}
