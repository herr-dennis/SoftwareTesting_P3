package model;

public class InsuranceRequest {

    /**
     * 1 = Basis
     * 2 = Komfort
     * 3 = Premium
     */
    private final int tarifKlasse;
    private final int alter ;
    private final int selbstBeteiligung;
    private final boolean raucher;
    private final boolean neukunde;


    public InsuranceRequest(int tarifKlasse, int alter , int selbstBeteilgigung, boolean raucher, boolean neukeunde) {
        this.tarifKlasse = tarifKlasse;

        if(alter > 120 || alter < 0){
            throw new IllegalArgumentException("alter should be between 0 and 120");
        }
        if (!(selbstBeteilgigung == 0 || selbstBeteilgigung == 500 || selbstBeteilgigung == 1000)) {
            throw new IllegalArgumentException("Ungültige Selbstbeteiligung: " + selbstBeteilgigung);
        }

        this.alter = alter;
        this.selbstBeteiligung =selbstBeteilgigung;
        this.raucher = raucher;
        this.neukunde = neukeunde;
    }


    public int getTarifKlasse() {
        return tarifKlasse;
    }

    public int getAlter() {
        return alter;
    }

    public int getSelbstBeteiligung() {
        return selbstBeteiligung;
    }

    public boolean isRaucher() {
        return raucher;
    }

    public boolean isNeukunde() {
        return neukunde;
    }
}
