package ludomania.model.bet.api;

public enum TrenteEtQuaranteBetType {
    COULEUR("Couleur", 1.0),
    ENVERSE("Enverse", 1.0);

    private final String displayName;
    private final double payout;

    TrenteEtQuaranteBetType(String displayName, double payout) {
        this.displayName = displayName;
        this.payout = payout;
    }

    public String getDisplayName(){
        return displayName;
    }

    public double getPayout(){
        return payout;
    }
}
