package ludomania.model.bet;

import ludomania.model.Pair;
import ludomania.model.croupier.roulette.RouletteColor;
import ludomania.model.croupier.roulette.RouletteWheel;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiFunction;

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
                numberedChoicesSuccessFn(INVALID_CHOICE),
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
                numberedChoicesSuccessFn(INVALID_CHOICES),
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
                numberedChoicesSuccessFn(INVALID_CHOICES),
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
                numberedChoicesSuccessFn(INVALID_CHOICES),
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
                numberedChoicesSuccessFn(INVALID_CHOICES),
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
                Collections.emptySet());
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
                Collections.emptySet());
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
                Collections.emptySet());
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
                Collections.emptySet());
    }

    /**
     * Creates a rouge bet.
     * @param amount the value of the bet.
     * @return the new bet instance.
     */
    public static RouletteBet<RouletteColor> rougeBet(final double amount) {
        return new RouletteBet<>(
                amount,
                RouletteBetType.ROUGE,
                (cr, choices) -> cr.getValue() == RouletteColor.ROUGE,
                Set.of(RouletteColor.ROUGE));
    }

    /**
     * Creates a noir bet.
     * @param amount the value of the bet.
     * @return the new bet instance.
     */
    public static RouletteBet<RouletteColor> noirBet(final double amount) {
        return new RouletteBet<>(
                amount,
                RouletteBetType.NOIR,
                (cr, choices) -> cr.getValue() == RouletteColor.NOIR,
                Set.of(RouletteColor.NOIR));
    }

    private static BiFunction<Pair<Integer, RouletteColor>, Set<Integer>, Boolean> numberedChoicesSuccessFn(
            final String msgPrefix) {
        return (cr, choices) -> {
            if (choices != null && !choices.isEmpty()) {
                return choices.stream().anyMatch(c -> Objects.equals(c, cr.getKey()));
            } else {
                throw new IllegalArgumentException(msgPrefix + choices);
            }
        };
    }
}
