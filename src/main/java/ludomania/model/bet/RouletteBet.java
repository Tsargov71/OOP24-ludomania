package ludomania.model.bet;

import ludomania.model.Pair;
import ludomania.model.bet.api.Bet;
import ludomania.model.bet.api.BetType;
import ludomania.model.croupier.roulette.RouletteColor;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiFunction;

/**
 * Represents the Roulette game Bet.
 * @param <T> the type of the choices made for the bet.
 */
public final class RouletteBet<T> extends Bet {
    private final BiFunction<Pair<Integer, RouletteColor>, Set<T>, Boolean> success;
    private final Set<T> choice;

    /**
     * Creates a new {@link RouletteBet}.
     * @param success the function that will evaluate if the bet is winning.
     * @param choice the choice operated by the player.
     * @param value the value of the bet.
     * @param type the type of the bet.
     */
    public RouletteBet(
            final double value,
            final BetType type,
            final BiFunction<Pair<Integer, RouletteColor>, Set<T>, Boolean> success,
            final Set<T> choice
    ) {
        super(value, type);
        this.success = success;
        this.choice = Collections.unmodifiableSet(choice);
    }

    /**
     *
     * @param wheelResult the result from the Roulette wheel.
     * @param choices the choices made for this bet.
     * @return the instance of private property {@code success}.
     */
    public Boolean isSuccessful(final Pair<Integer, RouletteColor> wheelResult, final Set<T> choices) {
        return success.apply(wheelResult, choices);
    }

    /**
     * Evaluates the bet win.
     * @return the bet win amount.
     */
    @Override
    public Double evaluate() {
        return getValue() + (getValue() * getType().getPayout());
    }

    @Override
    public String toString() {
        return String.format(
                "%1$,.2f $, %2$s on %3$s", this.getValue(), this.getType().getTypeName(), this.getChoice().toString());
    }

    /**
     * Gets the choiche on which the bet is placed.
     * @return the corresponding color or numbers.
     */
    public Set<T> getChoice() {
        return Collections.unmodifiableSet(this.choice);
    }
}
