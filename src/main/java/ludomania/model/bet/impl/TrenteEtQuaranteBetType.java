package ludomania.model.bet.impl;

import ludomania.model.bet.api.BetType;

public enum TrenteEtQuaranteBetType implements BetType {
    ROUGE("Rouge", 1.0),
    NOIR("Noir", 1.0),
    COULEUR("Couleur", 1.0),
    ENVERSE("Enverse", 1.0),
    DRAW("Draw", 0.0);

    private final String typeName;
    private final double payout;

    TrenteEtQuaranteBetType(final String displayName, final double payout) {
        this.typeName = displayName;
        this.payout = payout;
    }

    public String getTypeName() {
        return typeName;
    }

    public Double getPayout() {
        return payout;
    }
}
