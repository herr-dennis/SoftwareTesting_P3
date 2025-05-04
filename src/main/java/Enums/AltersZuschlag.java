package Enums;

public class AltersZuschlag {

    public static double getAlterZuschlag(int alter) {
        if (alter > 60) {
            return 1.4;
        } else if (alter > 40) {
            return 1.2;
        } else {
            return 1.0;
        }
    }
}
