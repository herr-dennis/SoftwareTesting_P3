package model;

import java.math.BigDecimal;
import java.util.Currency;

public class Main {
    public static void main(String[] args) {


        Money a = new Money(BigDecimal.valueOf(14141), Currency.getInstance("EUR"));
        Money b = new Money(BigDecimal.valueOf(141), Currency.getInstance("EUR"));
        Money c =  a.add(b);

        InsuranceRequest insuranceRequest = new InsuranceRequest( 1, 1,500,false,true);
        InsuranceCalculator calculator = new InsuranceCalculator();
        Money money =  calculator.calculatePremium(insuranceRequest);

        System.out.println(money.toString());


    }
}