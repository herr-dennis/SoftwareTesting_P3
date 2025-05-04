package Enums;
public class RaucherZuschlag {

    private static final int ZUSCHLAG = 50;

    public static int berechne(boolean istRaucher) {
        return istRaucher ? ZUSCHLAG : 0;
    }
}

