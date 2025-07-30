package ludomania.model.croupier;

import ludomania.model.Pair;
import ludomania.model.bet.RouletteBet;
import ludomania.model.bet.RouletteBetFactory;
import ludomania.model.croupier.roulette.RouletteColor;
import ludomania.model.croupier.roulette.RouletteCroupier;
import ludomania.model.game.impl.CounterResult;
import ludomania.model.player.RoulettePlayer;
import ludomania.model.player.api.Player;
import ludomania.model.wallet.impl.WalletImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class RouletteCroupierTest {
    private final CounterResult<Pair<Integer, RouletteColor>> mockResult22 =
            new CounterResult<>(new Pair<>(22, RouletteColor.NOIR));

    private Set<Integer> mockChoice;
    private RoulettePlayer mockPlayer;
    private RouletteCroupier mockCroupier;
    private double mockAmount;

    @BeforeEach
    void setUp() {
        final Integer mock22 = 22;
        mockChoice = Set.of(mock22);
        mockCroupier = new RouletteCroupier();
        mockAmount = 10.0;
        this.mockPlayer = new RoulettePlayer(new WalletImpl(1000.0), "TestPlayer");
    }

    @Test
    void checkBets() {
        final RouletteBet<Integer> mockPleinBet = RouletteBetFactory.pleinBet(this.mockChoice, this.mockAmount);
        this.mockCroupier.addBet(this.mockPlayer, mockPleinBet);

        final Map<Player, Double> winningBets = this.mockCroupier.checkBets(this.mockResult22);

        assertTrue(winningBets.containsKey(this.mockPlayer));
        assertEquals(mockPleinBet.evaluate(), winningBets.get(this.mockPlayer));
    }

    @Test
    void roundBet() {
        final RouletteBet<Integer> mockPleinBet = RouletteBetFactory.pleinBet(this.mockChoice, this.mockAmount);
        this.mockCroupier.addBet(this.mockPlayer, mockPleinBet);

        assertEquals(1, this.mockCroupier.getRoundBet().size());
        assertEquals(1, this.mockCroupier.getRoundBet()
                        .stream()
                        .filter(b -> b.getKey().equals(mockPlayer) && b.getValue().equals(mockPleinBet))
                        .count());
    }

    @Test
    void addBet() {
        final RouletteBet<Integer> mockPleinBet = RouletteBetFactory.pleinBet(this.mockChoice, this.mockAmount);
        this.mockCroupier.addBet(this.mockPlayer, mockPleinBet);

        assertFalse(this.mockCroupier.getRoundBet().isEmpty());
    }

    @Test
    void clearRound() {
        final RouletteBet<Integer> mockPleinBet = RouletteBetFactory.pleinBet(this.mockChoice, this.mockAmount);
        this.mockCroupier.addBet(this.mockPlayer, mockPleinBet);

        this.mockCroupier.clearRound();
        assertTrue(this.mockCroupier.getRoundBet().isEmpty());
    }

}
