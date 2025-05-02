import java.math.BigDecimal;
import java.util.Currency;

public class Main {
    public static void main(String[] args) {


        Money a = new Money(BigDecimal.valueOf(14141), Currency.getInstance("EUR"));
        Money b = new Money(BigDecimal.valueOf(141), Currency.getInstance("EUR"));
        Money c =  a.add(b);

       System.out.println(a.toString()+"\n"+c.toString());

    }
}