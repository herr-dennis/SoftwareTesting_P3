package Enums;

public class SelbstbeteiligungZuschlag {

    public static double getSelbstbeteiligungZus(int selbstbeteiligung) {

        if(selbstbeteiligung == 500){
            return 0.9;
        }
        else if(selbstbeteiligung == 1000){
            return 0.8;
        }else{
            return 1;
        }
    }
}
