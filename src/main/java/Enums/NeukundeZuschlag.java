package Enums;

public class NeukundeZuschlag {

    public static double getNeukundeZuschlag(boolean neukunde) {

        if(neukunde){
            return 1.05;
        }
        else{
            return 1.0;
        }
    }
}
