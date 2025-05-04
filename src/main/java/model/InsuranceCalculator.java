package model;
import Enums.*;

import java.math.BigDecimal;
import java.util.Currency;


public class InsuranceCalculator {

    Money calculatePremium(InsuranceRequest insuranceRequest){

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

      return new Money(result, Currency.getInstance("EUR"));
    }


}
