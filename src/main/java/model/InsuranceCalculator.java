package model;
import Enums.*;
import java.math.BigDecimal;
import java.util.Currency;


public class InsuranceCalculator {

    boolean dollar = false;
    private static final BigDecimal USD_CONVERSION_RATE = BigDecimal.valueOf(1.13);

    /**
     *  Zwei Konstruktor um per Boolean die Währung einzustellen
     */
    public InsuranceCalculator() {}
    public InsuranceCalculator(boolean dollar) { // dollar -> true -> Dollar
           this.dollar = dollar;

    }

    /**
     *  Berechnung der Prämien.
     *  Holt die Zuschläge von Klassen und Enums, anhand der Daten, die der User angibt
     * @param insuranceRequest ein Objekt mit Parametern, des Users, für die Berechnung
     * @return ein Money Objekt mit Währung EUR/ USD anhand der Einstellung über den Konstruktor
     */
  public Money calculatePremium(InsuranceRequest insuranceRequest){

        int TarifBetrag = Tarifklasse.getGrundbetragFromCode(insuranceRequest.getTarifKlasse());
        int rauschZuschlag = RaucherZuschlag.berechne(insuranceRequest.isRaucher());
        double altersFaktor = AltersZuschlag.getAlterZuschlag(insuranceRequest.getAlter());
        double neukundeFaktor = NeukundeZuschlag.getNeukundeZuschlag(insuranceRequest.isNeukunde());
        double selbstFaktor = SelbstbeteiligungZuschlag.getSelbstbeteiligungZus(insuranceRequest.getSelbstBeteiligung());

        BigDecimal result = new BigDecimal(TarifBetrag);
        result = result.add(BigDecimal.valueOf(rauschZuschlag));
        result = result.multiply(BigDecimal.valueOf(altersFaktor));
        result = result.multiply(BigDecimal.valueOf(neukundeFaktor));
        result = result.multiply(BigDecimal.valueOf(selbstFaktor));

        if(dollar){
            result = result.multiply(USD_CONVERSION_RATE);
            return new Money(result, Currency.getInstance("USD"));
        }

      return new Money(result, Currency.getInstance("EUR"));
    }


}
