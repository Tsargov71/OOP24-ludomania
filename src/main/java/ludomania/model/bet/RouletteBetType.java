package ludomania.model.bet;

import ludomania.model.bet.api.BetType;

/**
 * Represent the bet types for the Roulette game.
 */
public final class RouletteBetType {
    /**
     * Plein bet type.
     */
    public static final BetType PLEIN = new BetType() {
        @Override
        public String getTypeName() {
            return "PLEIN";
        }

        @Override
        public Double getPayout() {
            return PLEIN_PAYOUT;
        }
    };

    /**
     * Cheval bet type.
     */
    public static final BetType CHEVAL = new BetType() {
        @Override
        public String getTypeName() {
            return "CHEVAL";
        }

        @Override
        public Double getPayout() {
            return CHEVAL_PAYOUT;
        }
    };

    /**
     * Carre bet type.
     */
    public static final BetType CARRE = new BetType() {
        @Override
        public String getTypeName() {
            return "CARRE";
        }

        @Override
        public Double getPayout() {
            return CARRE_PAYOUT;
        }
    };

    /**
     * Douzaine bet type.
     */
    public static final BetType DOUZAINE = new BetType() {
        @Override
        public String getTypeName() {
            return "DOUZAINE";
        }

        @Override
        public Double getPayout() {
            return DOUZAINE_PAYOUT;
        }
    };

    /**
     * Colonne bet type.
     */
    public static final BetType COLONNE = new BetType() {
        @Override
        public String getTypeName() {
            return "COLONNE";
        }

        @Override
        public Double getPayout() {
            return COLONNE_PAYOUT;
        }
    };

    /**
     * Pair bet type.
     */
    public static final BetType PAIR = new BetType() {
        @Override
        public String getTypeName() {
            return "PAIR";
        }

        @Override
        public Double getPayout() {
            return PAIR_PAYOUT;
        }
    };

    /**
     * Impair bet type.
     */
    public static final BetType IMPAIR = new BetType() {
        @Override
        public String getTypeName() {
            return "IMPAIR";
        }

        @Override
        public Double getPayout() {
            return IMPAIR_PAYOUT;
        }
    };

    /**
     * Manque bet type.
     */
    public static final BetType MANQUE = new BetType() {
        @Override
        public String getTypeName() {
            return "MANQUE";
        }

        @Override
        public Double getPayout() {
            return MANQUE_PAYOUT;
        }
    };

    /**
     * Passe bet type.
     */
    public static final BetType PASSE = new BetType() {
        @Override
        public String getTypeName() {
            return "PASSE";
        }

        @Override
        public Double getPayout() {
            return PASSE_PAYOUT;
        }
    };

    /**
     * Rouge bet type.
     */
    public static final BetType ROUGE = new BetType() {
        @Override
        public String getTypeName() {
            return "ROUGE";
        }

        @Override
        public Double getPayout() {
            return ROUGE_PAYOUT;
        }
    };

    /**
     * Noir bet type.
     */
    public static final BetType NOIR = new BetType() {
        @Override
        public String getTypeName() {
            return "NOIR";
        }

        @Override
        public Double getPayout() {
            return NOIR_PAYOUT;
        }
    };

    private static final Double PLEIN_PAYOUT = 35.0;
    private static final Double CHEVAL_PAYOUT = 17.0;
    private static final Double CARRE_PAYOUT = 8.0;
    private static final Double DOUZAINE_PAYOUT = 2.0;
    private static final Double COLONNE_PAYOUT = 2.0;
    private static final Double PAIR_PAYOUT = 1.0;
    private static final Double IMPAIR_PAYOUT = 1.0;
    private static final Double MANQUE_PAYOUT = 1.0;
    private static final Double PASSE_PAYOUT = 1.0;
    private static final Double ROUGE_PAYOUT = 1.0;
    private static final Double NOIR_PAYOUT = 1.0;

    private RouletteBetType() { }
}
