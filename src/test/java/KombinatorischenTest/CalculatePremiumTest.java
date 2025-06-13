package KombinatorischenTest;

import model.InsuranceCalculator;
import model.InsuranceRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatePremiumTest {
    InsuranceRequest t_1;
    InsuranceRequest t_2;
    InsuranceRequest t_3;
    InsuranceRequest t_4;
    InsuranceRequest t_5;
    InsuranceRequest t_6;

    InsuranceCalculator calculatePremium;


    final BigDecimal t1_result = new BigDecimal("200.00");
    final BigDecimal t2_result = new BigDecimal("330.75");
    final BigDecimal t3_result = new BigDecimal("490");
    final BigDecimal t4_result = new BigDecimal("283.50");
    final BigDecimal t5_result = new BigDecimal("441.00");
    final BigDecimal t6_result = new BigDecimal("315");


    @BeforeEach
    void setUp() {

        calculatePremium = new InsuranceCalculator();

        t_1 = new InsuranceRequest(1, 1,   0,   false, false); // Zeile 1
        t_2= new InsuranceRequest(1, 120, 500, true,  true ); // Zeile 2
        t_3=   new InsuranceRequest(2, 120, 0,   true,  false); // Zeile 3
        t_4=  new InsuranceRequest(2, 1,   500, false, true ); // Zeile 4
        t_5=   new InsuranceRequest(2, 120, 0,   false, true ); // Zeile 5
        t_6=  new InsuranceRequest(2, 1,   500, true,  false); // Zeile 6

    }



   @Test
    void testCalculatePremium() {

        assertEquals(t1_result.stripTrailingZeros(), calculatePremium.calculatePremium(t_1).getAmount().stripTrailingZeros());
       assertEquals(t2_result.stripTrailingZeros(), calculatePremium.calculatePremium(t_2).getAmount().stripTrailingZeros());
       assertEquals(t3_result.stripTrailingZeros(), calculatePremium.calculatePremium(t_3).getAmount().stripTrailingZeros());
       assertEquals(t4_result.stripTrailingZeros(), calculatePremium.calculatePremium(t_4).getAmount().stripTrailingZeros());
       assertEquals(t5_result.stripTrailingZeros(), calculatePremium.calculatePremium(t_5).getAmount().stripTrailingZeros());
       assertEquals(t6_result.stripTrailingZeros(), calculatePremium.calculatePremium(t_6).getAmount().stripTrailingZeros());

   }




}
