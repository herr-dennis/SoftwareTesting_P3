package Enums;

public enum Tarifklasse {
    BASIS(1, 200),
    KOMFORT(2, 300),
    PREMIUM(3, 400);

    private final int code;
    private final int grundbetrag;

    Tarifklasse(int code, int grundbetrag) {
        this.code = code;
        this.grundbetrag = grundbetrag;
    }

    public int getGrundbetrag() {
        return grundbetrag;
    }
    public static int getGrundbetragFromCode(int code) {
        for (Tarifklasse t : values()) {
            if (t.code == code) {
                return t.getGrundbetrag();
            }
        }
        throw new IllegalArgumentException("Unbekannter Tarif: " + code);
    }

}
