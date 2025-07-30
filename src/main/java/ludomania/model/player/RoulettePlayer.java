package ludomania.model.player;

import ludomania.model.bet.RouletteBetFactory;
import ludomania.model.bet.api.Bet;
import ludomania.model.bet.api.BetType;
import ludomania.model.player.api.Player;
import ludomania.model.wallet.api.Wallet;

import java.util.Set;

/**
 * Implements {@link Player} for the Roulette game.
 *  * <p>
 *  * Handles the creation of valid bets for this specific game and player money managing logic.
 */
public class RoulettePlayer extends Player {
    private Double betAmount = 0.0;

    /**
     * Creates a new Roulette player.
     * @param wallet the instance of the player {@link Wallet}.
     * @param username the player username.
     */
    public RoulettePlayer(final Wallet wallet, final String username) {
        super(wallet, username);
    }

    /**
     * {@inheritDoc}
     * @param amount the amount of money to bet
     * @param type the type of bet
     * @return
     */
    @Override
    public Bet makeBet(final Double amount, final BetType type) {
        throw new UnsupportedOperationException("Not supported.");
    }

    /**
     * Gets the next bet to place amount.
     * @return current amount for the next bet to place.
     */
    public Double getBetAmount() {
        return this.betAmount;
    }

    /**
     * Increase the next bet amount.
     * @param amount incrementation value.
     */
    public void addBetAmount(final Double amount) {
        this.betAmount += amount;
    }

    /**
     * Resets next bet to place amount.
     */
    public void resetBetAmount() {
        this.betAmount = 0.0;
    }

    /**
     * Moves the next bet to place amount to the player wallet.
     */
    public void restoreBalance() {
        this.deposit(this.getBetAmount());
        this.resetBetAmount();
    }

    /**
     * Creates a bet.
     * @param amount value of the bet.
     * @param type type of the bet.
     * @param choice choice of the player.
     * @return the new {@link ludomania.model.bet.RouletteBet}.
     */
    public Bet makeBet(final Double amount, final BetType type, final Set<Integer> choice) {
        if (amount >= 0) {
            switch (type.getTypeName()) {
                case "PLEIN" -> {
                    return RouletteBetFactory.pleinBet(choice, amount);
                }
                case "CHEVAL" -> {
                    return RouletteBetFactory.chevalBet(choice, amount);
                }
                case "CARRE" -> {
                    return RouletteBetFactory.carreBet(choice, amount);
                }
                case "DOUZAINE" -> {
                    return RouletteBetFactory.douzaineBet(choice, amount);
                }
                case "COLONNE" -> {
                    return RouletteBetFactory.colonneBet(choice, amount);
                }
                case "PAIR" -> {
                    return RouletteBetFactory.pairBet(amount);
                }
                case "IMPAIR" -> {
                    return RouletteBetFactory.impairBet(amount);
                }
                case "PASSE" -> {
                    return RouletteBetFactory.passeBet(amount);
                }
                case "MANQUE" -> {
                    return RouletteBetFactory.manqueBet(amount);
                }
                case "ROUGE" -> {
                    return RouletteBetFactory.rougeBet(amount);
                }
                case "NOIR" -> {
                    return RouletteBetFactory.noirBet(amount);
                }
                default -> throw new IllegalArgumentException("Invalid bet type " + type);
            }
        } else {
            throw new IllegalArgumentException("Cannot place a bet with amount 0");
        }
    }
}
