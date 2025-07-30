package ludomania.model.bet;

import ludomania.model.Pair;
import ludomania.model.croupier.roulette.RouletteColor;
import ludomania.model.croupier.roulette.RouletteWheel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("RouletteBet Factory Methods Test")
class RouletteBetTest {
    private double mockAmount;
    private final Pair<Integer, RouletteColor> odd17 = new Pair<>(17, RouletteColor.NOIR);
    private final Pair<Integer, RouletteColor> odd11 = new Pair<>(11, RouletteColor.NOIR);
    private final Pair<Integer, RouletteColor> odd29 = new Pair<>(29, RouletteColor.NOIR);
    private final Pair<Integer, RouletteColor> pair22 = new Pair<>(22, RouletteColor.NOIR);
    private final Pair<Integer, RouletteColor> pair4 = new Pair<>(4, RouletteColor.NOIR);
    private final Pair<Integer, RouletteColor> pair14 = new Pair<>(14, RouletteColor.ROUGE);
    private final Pair<Integer, RouletteColor> pair12 = new Pair<>(12, RouletteColor.ROUGE);
    private final Pair<Integer, RouletteColor> pair36 = new Pair<>(36, RouletteColor.ROUGE);
    private final Pair<Integer, RouletteColor> pair18 = new Pair<>(18, RouletteColor.NOIR);
    private final Pair<Integer, RouletteColor> odd1 = new Pair<>(1, RouletteColor.ROUGE);
    private final Pair<Integer, RouletteColor> n0 = new Pair<>(0, RouletteColor.VERT);
    private final Pair<Integer, RouletteColor> rouge1 = new Pair<>(1, RouletteColor.ROUGE);
    private final Pair<Integer, RouletteColor> rouge3 = new Pair<>(3, RouletteColor.ROUGE);
    private final Pair<Integer, RouletteColor> rouge5 = new Pair<>(5, RouletteColor.ROUGE);
    private final Pair<Integer, RouletteColor> rouge7 = new Pair<>(7, RouletteColor.ROUGE);
    private final Pair<Integer, RouletteColor> rouge9 = new Pair<>(9, RouletteColor.ROUGE);
    private final Pair<Integer, RouletteColor> noir2 = new Pair<>(2, RouletteColor.NOIR);
    private final Pair<Integer, RouletteColor> noir4 = new Pair<>(4, RouletteColor.NOIR);
    private final Pair<Integer, RouletteColor> noir6 = new Pair<>(6, RouletteColor.NOIR);
    private final Pair<Integer, RouletteColor> vert0 = new Pair<>(0, RouletteColor.VERT);
    private final Pair<Integer, RouletteColor> chevalChoice25 = new Pair<>(25, RouletteColor.ROUGE);
    private final Pair<Integer, RouletteColor> chevalChoice26 = new Pair<>(26, RouletteColor.NOIR);
    private final Pair<Integer, RouletteColor> carreChoice25 = new Pair<>(25, RouletteColor.ROUGE);
    private final Pair<Integer, RouletteColor> carreChoice26 = new Pair<>(26, RouletteColor.NOIR);
    private final Pair<Integer, RouletteColor> carreChoice28 = new Pair<>(28, RouletteColor.NOIR);
    private final Pair<Integer, RouletteColor> carreChoice29 = new Pair<>(29, RouletteColor.ROUGE);

    @BeforeEach
    void setUp() {
        mockAmount = 10.0;
    }

    @Test
    void testImpairBet() {
        final RouletteBet<Integer> bet = RouletteBetFactory.impairBet(mockAmount);

        assertNotNull(bet);
        assertEquals(mockAmount, bet.getValue());
        assertEquals(RouletteBetType.IMPAIR, bet.getType());

        assertTrue(bet.isSuccessful(this.odd17, bet.getChoice()));
        assertTrue(bet.isSuccessful(this.odd11, bet.getChoice()));
        assertTrue(bet.isSuccessful(this.odd29, bet.getChoice()));

        assertFalse(bet.isSuccessful(this.pair22, bet.getChoice()));
        assertFalse(bet.isSuccessful(this.pair4, bet.getChoice()));
        assertFalse(bet.isSuccessful(this.n0, bet.getChoice()));
    }

    @Test
    void testPairBet() {
        final RouletteBet<Integer> bet = RouletteBetFactory.pairBet(mockAmount);

        assertNotNull(bet);
        assertEquals(mockAmount, bet.getValue());
        assertEquals(RouletteBetType.PAIR, bet.getType());

        assertTrue(bet.isSuccessful(this.pair22, bet.getChoice()));
        assertTrue(bet.isSuccessful(this.pair4, bet.getChoice()));
        assertTrue(bet.isSuccessful(this.pair14, bet.getChoice()));

        assertFalse(bet.isSuccessful(this.n0, bet.getChoice()));
        assertFalse(bet.isSuccessful(this.odd11, bet.getChoice()));
        assertFalse(bet.isSuccessful(this.odd17, bet.getChoice()));
    }

    @Test
    void testPasseBet() {
        final RouletteBet<Integer> bet = RouletteBetFactory.passeBet(mockAmount);

        assertNotNull(bet);
        assertEquals(mockAmount, bet.getValue());
        assertEquals(RouletteBetType.PASSE, bet.getType());

        assertTrue(bet.isSuccessful(this.pair22, bet.getChoice()));
        assertTrue(bet.isSuccessful(this.odd29, bet.getChoice()));
        assertTrue(bet.isSuccessful(this.pair36, bet.getChoice()));

        assertFalse(bet.isSuccessful(this.n0, bet.getChoice()));
        assertFalse(bet.isSuccessful(this.odd17, bet.getChoice()));
        assertFalse(bet.isSuccessful(this.pair14, bet.getChoice()));
    }

    @Test
    void testManqueBet() {
        final RouletteBet<Integer> bet = RouletteBetFactory.manqueBet(mockAmount);

        assertNotNull(bet);
        assertEquals(mockAmount, bet.getValue());
        assertEquals(RouletteBetType.MANQUE, bet.getType());

        assertTrue(bet.isSuccessful(this.odd1, bet.getChoice()));
        assertTrue(bet.isSuccessful(this.odd17, bet.getChoice()));
        assertTrue(bet.isSuccessful(this.pair18, bet.getChoice()));

        assertFalse(bet.isSuccessful(this.pair22, bet.getChoice()));
        assertFalse(bet.isSuccessful(this.odd29, bet.getChoice()));
        assertFalse(bet.isSuccessful(this.pair36, bet.getChoice()));
    }

    @Test
    void testRougeBet() {
        final RouletteBet<RouletteColor> bet = RouletteBetFactory.rougeBet(mockAmount);

        assertNotNull(bet);
        assertEquals(mockAmount, bet.getValue());
        assertEquals(RouletteBetType.ROUGE, bet.getType());

        assertTrue(bet.isSuccessful(this.rouge1, bet.getChoice()));
        assertTrue(bet.isSuccessful(this.rouge3, bet.getChoice()));
        assertTrue(bet.isSuccessful(this.rouge5, bet.getChoice()));
        assertTrue(bet.isSuccessful(this.rouge7, bet.getChoice()));
        assertTrue(bet.isSuccessful(this.rouge9, bet.getChoice()));

        assertFalse(bet.isSuccessful(this.noir2, bet.getChoice()));
        assertFalse(bet.isSuccessful(this.noir4, bet.getChoice()));
        assertFalse(bet.isSuccessful(this.noir6, bet.getChoice()));
        assertFalse(bet.isSuccessful(this.vert0, bet.getChoice()));
    }

    @Test
    void testNoirBet() {
        final RouletteBet<RouletteColor> bet = RouletteBetFactory.noirBet(mockAmount);

        assertNotNull(bet);
        assertEquals(mockAmount, bet.getValue());
        assertEquals(RouletteBetType.NOIR, bet.getType());

        assertTrue(bet.isSuccessful(this.noir2, bet.getChoice()));
        assertTrue(bet.isSuccessful(this.noir4, bet.getChoice()));
        assertTrue(bet.isSuccessful(this.noir6, bet.getChoice()));

        assertFalse(bet.isSuccessful(this.rouge1, bet.getChoice()));
        assertFalse(bet.isSuccessful(this.rouge3, bet.getChoice()));
        assertFalse(bet.isSuccessful(this.rouge5, bet.getChoice()));
        assertFalse(bet.isSuccessful(this.vert0, bet.getChoice()));
    }

    @Test
    void pleinBet() {
        final Set<Integer> mockChoice = Set.of(this.pair22.getKey());
        final RouletteBet<Integer> bet = RouletteBetFactory.pleinBet(mockChoice, mockAmount);

        assertNotNull(bet);
        assertEquals(mockAmount, bet.getValue());
        assertEquals(mockChoice, bet.getChoice());
        assertEquals(RouletteBetType.PLEIN, bet.getType());

        assertTrue(bet.isSuccessful(this.pair22, bet.getChoice()));

        assertFalse(bet.isSuccessful(this.pair18, bet.getChoice()));
    }

    @Test
    void chevalBet() {
        final Set<Integer> mockChoice = Set.of(this.chevalChoice25.getKey(), this.chevalChoice26.getKey());
        final RouletteBet<Integer> bet = RouletteBetFactory.chevalBet(mockChoice, mockAmount);

        assertNotNull(bet);
        assertEquals(mockAmount, bet.getValue());
        assertEquals(mockChoice, bet.getChoice());
        assertEquals(RouletteBetType.CHEVAL, bet.getType());

        assertTrue(bet.isSuccessful(this.chevalChoice25, bet.getChoice()));
        assertTrue(bet.isSuccessful(this.chevalChoice26, bet.getChoice()));

        assertFalse(bet.isSuccessful(this.odd17, bet.getChoice()));
        assertFalse(bet.isSuccessful(this.n0, bet.getChoice()));
    }

    @Test
    void carreBet() {
        final Set<Integer> mockChoice = Set.of(
                this.carreChoice25.getKey(),
                this.carreChoice26.getKey(),
                this.carreChoice28.getKey(),
                this.carreChoice29.getKey());
        final RouletteBet<Integer> bet = RouletteBetFactory.carreBet(mockChoice, mockAmount);

        assertNotNull(bet);
        assertEquals(mockAmount, bet.getValue());
        assertEquals(mockChoice, bet.getChoice());
        assertEquals(RouletteBetType.CARRE, bet.getType());

        assertTrue(bet.isSuccessful(this.carreChoice25, bet.getChoice()));
        assertTrue(bet.isSuccessful(this.carreChoice26, bet.getChoice()));
        assertTrue(bet.isSuccessful(this.carreChoice28, bet.getChoice()));
        assertTrue(bet.isSuccessful(this.carreChoice29, bet.getChoice()));

        assertFalse(bet.isSuccessful(this.n0, bet.getChoice()));
        assertFalse(bet.isSuccessful(this.pair22, bet.getChoice()));
        assertFalse(bet.isSuccessful(this.pair36, bet.getChoice()));
        assertFalse(bet.isSuccessful(this.pair18, bet.getChoice()));
    }

    @Test
    void douzaineBet() {
        final Set<Integer> mockChoice = RouletteWheel.firstDouzaine();
        final RouletteBet<Integer> bet = RouletteBetFactory.douzaineBet(mockChoice, mockAmount);

        assertNotNull(bet);
        assertEquals(mockAmount, bet.getValue());
        assertEquals(mockChoice, bet.getChoice());
        assertEquals(RouletteBetType.DOUZAINE, bet.getType());

        assertTrue(bet.isSuccessful(this.odd1, bet.getChoice()));
        assertTrue(bet.isSuccessful(this.pair4, bet.getChoice()));
        assertTrue(bet.isSuccessful(this.pair12, bet.getChoice()));

        assertFalse(bet.isSuccessful(this.pair18, bet.getChoice()));
        assertFalse(bet.isSuccessful(this.pair14, bet.getChoice()));
        assertFalse(bet.isSuccessful(this.odd29, bet.getChoice()));
    }

    @Test
    void colonneBet() {
        final Set<Integer> mockChoice = RouletteWheel.SECOND_COLONNE;
        final RouletteBet<Integer> bet = RouletteBetFactory.colonneBet(mockChoice, mockAmount);

        assertNotNull(bet);
        assertEquals(mockAmount, bet.getValue());
        assertEquals(mockChoice, bet.getChoice());
        assertEquals(RouletteBetType.COLONNE, bet.getType());

        assertTrue(bet.isSuccessful(this.rouge5, bet.getChoice()));
        assertTrue(bet.isSuccessful(this.odd11, bet.getChoice()));
        assertTrue(bet.isSuccessful(this.odd29, bet.getChoice()));

        assertFalse(bet.isSuccessful(this.pair12, bet.getChoice()));
        assertFalse(bet.isSuccessful(this.noir6, bet.getChoice()));
        assertFalse(bet.isSuccessful(this.pair36, bet.getChoice()));
    }
}
