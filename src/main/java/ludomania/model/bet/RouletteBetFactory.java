package ludomania.model.bet;

import ludomania.model.croupier.roulette.RouletteColor;
import ludomania.model.croupier.roulette.RouletteWheel;

import java.util.Objects;
import java.util.Set;

/**
 * Represents a {@code factory} for the roulette game bets.
 */
public final class RouletteBetFactory {
    private static final String INVALID_CHOICE = "Invalid choice: ";
    private static final String INVALID_CHOICES = "Invalid choices: ";

    private RouletteBetFactory() { }

    /**
     * Creates a plein bet.
     * @param choice the player choice on which to place the bet.
     * @param amount the value of the bet.
     * @return the new bet instance.
     */
    public static RouletteBet<Integer> pleinBet(final Set<Integer> choice, final double amount) {
        return new RouletteBet<>(
                amount,
                RouletteBetType.PLEIN,
                (cr, choices) -> {
                    if (choices != null && !choices.isEmpty()) {
                        return choices.stream().anyMatch(c -> Objects.equals(c, cr.getKey()));
                    } else {
                        throw new IllegalArgumentException(INVALID_CHOICE + choices);
                    }
                },
                choice);
    }

    /**
     * Creates a cheval bet.
     * @param choice the player choice on which to place the bet.
     * @param amount the value of the bet.
     * @return the new bet instance.
     */
    public static RouletteBet<Integer> chevalBet(final Set<Integer> choice, final double amount) {
        return new RouletteBet<>(
                amount,
                RouletteBetType.CHEVAL,
                (cr, choices) -> {
                    if (choices != null && !choices.isEmpty()) {
                        return choices.stream().anyMatch(c -> Objects.equals(c, cr.getKey()));
                    } else {
                        throw new IllegalArgumentException(INVALID_CHOICES + choices);
                    }
                },
                choice);
    }

    /**
     * Creates a carre bet.
     * @param choice the player choice on which to place the bet.
     * @param amount the value of the bet.
     * @return the new bet instance.
     */
    public static RouletteBet<Integer> carreBet(final Set<Integer> choice, final double amount) {
        return new RouletteBet<>(
                amount,
                RouletteBetType.CARRE,
                (cr, choices) -> {
                    if (choices != null && !choices.isEmpty()) {
                        return choices.stream().anyMatch(c -> Objects.equals(c, cr.getKey()));
                    } else {
                        throw new IllegalArgumentException(INVALID_CHOICES + choices);
                    }
                },
                choice);
    }

    /**
     * Creates a douzaine bet.
     * @param choice the player choice on which to place the bet.
     * @param amount the value of the bet.
     * @return the new bet instance.
     */
    public static RouletteBet<Integer> douzaineBet(final Set<Integer> choice, final double amount) {
        return new RouletteBet<>(
                amount,
                RouletteBetType.DOUZAINE,
                (cr, choices) -> {
                    if (choices != null && !choices.isEmpty()) {
                        return choices.stream().anyMatch(c -> Objects.equals(c, cr.getKey()));
                    } else {
                        throw new IllegalArgumentException(INVALID_CHOICES + choices);
                    }
                },
                choice);
    }

    /**
     * Creates a colonne bet.
     * @param choice the player choice on which to place the bet.
     * @param amount the value of the bet.
     * @return the new bet instance.
     */
    public static RouletteBet<Integer> colonneBet(final Set<Integer> choice, final double amount) {
        return new RouletteBet<>(
                amount,
                RouletteBetType.COLONNE,
                (cr, choices) -> {
                    if (choices != null && !choices.isEmpty()) {
                        return choices.stream().anyMatch(c -> Objects.equals(c, cr.getKey()));
                    } else {
                        throw new IllegalArgumentException(INVALID_CHOICES + choices);
                    }
                },
                choice);
    }

    /**
     * Creates a pair bet.
     * @param amount the value of the bet.
     * @return the new bet instance.
     */
    public static RouletteBet<Integer> pairBet(final double amount) {
        return new RouletteBet<>(
                amount,
                RouletteBetType.PAIR,
                (cr, choices) -> cr.getKey() != 0 && cr.getKey() % 2 == 0,
                Set.of());
    }

    /**
     * Creates a impair bet.
     * @param amount the value of the bet.
     * @return the new bet instance.
     */
    public static RouletteBet<Integer> impairBet(final double amount) {
        return new RouletteBet<>(
                amount,
                RouletteBetType.IMPAIR,
                (cr, choices) -> cr.getKey() != 0 && cr.getKey() % 2 != 0,
                Set.of());
    }

    /**
     * Creates a passe bet.
     * @param amount the value of the bet.
     * @return the new bet instance.
     */
    public static RouletteBet<Integer> passeBet(final double amount) {
        return new RouletteBet<>(
                amount,
                RouletteBetType.PASSE,
                (cr, choices) -> RouletteWheel.passe().stream().anyMatch(c -> Objects.equals(c, cr.getKey())),
                Set.of());
    }

    /**
     * Creates a manque bet.
     * @param amount the value of the bet.
     * @return the new bet instance.
     */
    public static RouletteBet<Integer> manqueBet(final double amount) {
        return new RouletteBet<>(
                amount,
                RouletteBetType.MANQUE,
                (cr, choices) -> RouletteWheel.manque().stream().anyMatch(c -> Objects.equals(c, cr.getKey())),
                Set.of());
    }

    /**
     * Creates a rouge bet.
     * @param amount the value of the bet.
     * @return the new bet instance.
     */
    public static RouletteBet<Integer> rougeBet(final double amount) {
        return new RouletteBet<>(
                amount,
                RouletteBetType.ROUGE,
                (cr, choices) -> cr.getValue() == RouletteColor.ROUGE,
                Set.of());
    }

    /**
     * Creates a noir bet.
     * @param amount the value of the bet.
     * @return the new bet instance.
     */
    public static RouletteBet<Integer> noirBet(final double amount) {
        return new RouletteBet<>(
                amount,
                RouletteBetType.NOIR,
                (cr, choices) -> cr.getValue() == RouletteColor.NOIR,
                Set.of());
    }
}
