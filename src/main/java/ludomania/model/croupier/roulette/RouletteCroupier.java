package ludomania.model.croupier.roulette;

import ludomania.model.Pair;
import ludomania.model.bet.RouletteBet;
import ludomania.model.croupier.api.Croupier;
import ludomania.model.game.impl.CounterResult;
import ludomania.model.player.api.Player;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Croupier for Roulette game.
 * <p>
 *     Keeps track of the round bets and evaluates them.
 * </p>
 */
public class RouletteCroupier extends Croupier<Pair<Integer, RouletteColor>> {

    /**
     * Instantiate round bets to empty collection.
     */
    public RouletteCroupier() {
        super(new ArrayList<>());
    }

    /**
     * {@inheritDoc}
     * @param result the outcome of the round to use for bet evaluation.
     * @return a map of players to their winnings (if any).
     */
    @Override
    public Map<Player, Double> checkBets(final CounterResult<Pair<Integer, RouletteColor>> result) {
        final Map<Player, Double> winningBets;

        if (this.getRoundBet().stream().allMatch(b -> b.getValue() instanceof RouletteBet<?>)) {
            winningBets = this.getRoundBet()
                    .stream()
                    .filter(b -> checkRouletteBetSuccess((RouletteBet<?>) b.getValue(), result.getResult()))
                    .collect(Collectors.toUnmodifiableMap(Pair::getKey, b -> b.getValue().evaluate(), Double::sum));
        } else {
            throw new IllegalArgumentException("Bets must be of type RouletteBet");
        }

        return winningBets;
    }

    /**
     * Helper method to capture the wildcard type and safely invoke isSuccessful.
     * @param bet The RouletteBet with an unknown generic type.
     * @param wheelResult The result of the roulette wheel spin.
     * @return true if the bet is successful, false otherwise.
     * @param <T> the type of choice made for the bet.
     */
    private <T> boolean checkRouletteBetSuccess(
            final RouletteBet<T> bet,
            final Pair<Integer, RouletteColor> wheelResult) {
        return bet.isSuccessful(wheelResult, bet.getChoice());
    }
}
