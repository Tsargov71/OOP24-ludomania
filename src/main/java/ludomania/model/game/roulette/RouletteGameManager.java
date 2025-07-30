package ludomania.model.game.roulette;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import ludomania.model.Pair;
import ludomania.model.bet.RouletteBetType;
import ludomania.model.bet.api.Bet;
import ludomania.model.croupier.roulette.RouletteColor;
import ludomania.model.croupier.roulette.RouletteCroupier;
import ludomania.model.croupier.roulette.RouletteWheel;
import ludomania.model.game.impl.CounterResult;
import ludomania.model.player.RoulettePlayer;
import ludomania.model.player.api.Player;
import ludomania.model.wallet.impl.WalletImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the core function that manages all behaviour aspects concerning Roulette game logic.
 */
public class RouletteGameManager {
    private static final String DEFAULT_PLAYER_USERNAME = "DemoPlayer";

    private final RouletteCroupier rouletteCroupier;
    private final Map<String, RoulettePlayer> players;
    private RoulettePlayer currentPlayer;
    private CounterResult<Pair<Integer, RouletteColor>> lastResult;

    /**
     * Gets the current set of players for the game if present, otherwise creates a demo player. Sets the current player.
     * @param rouletteCroupier the croupier instance for the game.
     * @param players the set of player for the game.
     */
    @SuppressFBWarnings(
            value = "EI2",
            justification = "References to RouletteCropier is intentional, since no direct data is used in this class"
    )
    public RouletteGameManager(final RouletteCroupier rouletteCroupier, final Set<RoulettePlayer> players) {
        this.rouletteCroupier = rouletteCroupier;
        if (players == null) {
            this.players = new HashMap<>();
        } else {
            this.players = new HashMap<>(players.stream().collect(Collectors.toMap(Player::getUsername, p -> p)));
        }

        if (players != null && !players.isEmpty()) {
            final Optional<RoulettePlayer> firstPlayer = players.stream().findFirst();
            this.currentPlayer = firstPlayer.orElseGet(() -> {
                final RoulettePlayer demoPlayer = new RoulettePlayer(new WalletImpl(1000.0), DEFAULT_PLAYER_USERNAME);
                this.players.put(demoPlayer.getUsername(), demoPlayer);
                return demoPlayer;
            });
        } else {
            final RoulettePlayer demoPlayer = new RoulettePlayer(new WalletImpl(1000.0), DEFAULT_PLAYER_USERNAME);
            this.players.put(demoPlayer.getUsername(), demoPlayer);
            this.currentPlayer = demoPlayer;
        }
    }

    /**
     * Gets the current single player for the game if present, otherwise creates a demo player. Sets the current player.
     * @param rouletteCroupier the croupier instance for the game.
     * @param player the single player for the game.
     */
    @SuppressFBWarnings(
            value = "EI2",
            justification = "References to RouletteCropier is intentional, since no direct data is used in this class"
    )
    public RouletteGameManager(final RouletteCroupier rouletteCroupier, final RoulettePlayer player) {
        this.rouletteCroupier = rouletteCroupier;

        final RoulettePlayer singlePlayer =
                player == null ? new RoulettePlayer(new WalletImpl(1000.0), DEFAULT_PLAYER_USERNAME) : player;

        this.players = new HashMap<>();
        this.players.put(singlePlayer.getUsername(), singlePlayer);
        this.currentPlayer = singlePlayer;
    }

    /**
     * Creates ademo player for the game and sets it to be the current player.
     * @param rouletteCroupier the croupier instance for the game.
     */
    @SuppressFBWarnings(
            value = "EI2",
            justification = "References to RouletteCropier is intentional, since no direct data is used in this class"
    )
    public RouletteGameManager(final RouletteCroupier rouletteCroupier) {
        this.rouletteCroupier = Set.of(rouletteCroupier).stream().findFirst().get();
        final RoulettePlayer demoPlayer = new RoulettePlayer(new WalletImpl(1000.0), DEFAULT_PLAYER_USERNAME);
        this.players = new HashMap<>();
        this.players.put(demoPlayer.getUsername(), demoPlayer);
        this.currentPlayer = demoPlayer;
    }

    /**
     * Gets next roulette wheel result.
     * @return s {@link CounterResult} of {@link Pair} containing number and related color.
     */
    public CounterResult<Pair<Integer, RouletteColor>> runGame() {
        this.lastResult = new CounterResult<>(RouletteWheel.random());
        return this.lastResult;
    }

    /**
     * Checks all the winning bets of the round and pays corresponding players. Cleans all the round bets.
     */
    public void evaluateGame() {
        final Map<Player, Double> winners =  this.rouletteCroupier.checkBets(this.lastResult);

        winners.forEach((player, amount) -> {
            if (this.players.containsKey(player.getUsername())) {
                this.players.get(player.getUsername()).deposit(amount);
            }
        });

        this.rouletteCroupier.clearRound();
    }

    /**
     * Checks if the current player has still money.
     * @return true if success, false otherwise.
     */
    public boolean checkGameOver() {
        return this.currentPlayer.getBalance() == 0;
    }

    /**
     * Sets the new current player, if present.
     * @param username the player username.
     */
    public void setCurrentPlayer(final String username) {
        if (this.players.containsKey(username)) {
            this.currentPlayer = this.players.get(username);
        }
    }

    /**
     * Places a plein bet, if possible.
     * @param event the mouse click event on the plein bet button.
     */
    public void pleinBet(final MouseEvent event) {
        try {
            if (event.getSource() instanceof Button button) {
                final int id = Integer.parseInt(button.getId());
                this.rouletteCroupier.addBet(
                        this.currentPlayer,
                        this.currentPlayer.makeBet(this.currentPlayer.getBetAmount(), RouletteBetType.PLEIN, Set.of(id)));

                this.currentPlayer.resetBetAmount();
            } else {
                this.currentPlayer.restoreBalance();
            }
        } catch (final IllegalArgumentException e) {
            this.currentPlayer.restoreBalance();
        }
    }

    /**
     * Places a cheval bet, if possible.
     * @param event the mouse click event on the cheval bet button.
     */
    public void chevalBet(final MouseEvent event) {
        try {
            if (event.getSource() instanceof Separator separator) {
                final String id = separator.getId();
                final Set<Integer> choices =
                        Arrays.stream(id.split("-")).map(Integer::parseInt).collect(Collectors.toSet());

                this.rouletteCroupier.addBet(
                        this.currentPlayer,
                        this.currentPlayer.makeBet(this.currentPlayer.getBetAmount(), RouletteBetType.CHEVAL, choices));

                this.currentPlayer.resetBetAmount();
            } else {
                this.currentPlayer.restoreBalance();
            }
        } catch (final IllegalArgumentException e) {
            this.currentPlayer.restoreBalance();
        }
    }

    /**
     * Places a carre bet, if possible.
     * @param event the mouse click event on the carre bet button.
     */
    public void carreBet(final MouseEvent event) {
        try {
            if (event.getSource() instanceof Button button) {
                final String id = button.getId();
                final Set<Integer> choices =
                        Arrays.stream(id.split("-")).map(Integer::parseInt).collect(Collectors.toSet());

                this.rouletteCroupier.addBet(
                        this.currentPlayer,
                        this.currentPlayer.makeBet(this.currentPlayer.getBetAmount(), RouletteBetType.CARRE, choices));

                this.currentPlayer.resetBetAmount();
            } else {
                this.currentPlayer.restoreBalance();
            }
        } catch (final IllegalArgumentException e) {
            this.currentPlayer.restoreBalance();
        }
    }

    /**
     * Places a colonne bet, if possible.
     * @param event the mouse click event on the colonne bet button.
     */
    public void colonneBet(final MouseEvent event) {
        try {
            if (event.getSource() instanceof Button button) {
                final String id = button.getId();
                final Set<Integer> choices;
                switch (id.charAt(0)) {
                    case 'b' -> choices = RouletteWheel.FIRST_COLONNE;
                    case 'm' -> choices = RouletteWheel.SECOND_COLONNE;
                    case 't' -> choices = RouletteWheel.THIRD_COLONNE;
                    default -> {
                        this.currentPlayer.restoreBalance();
                        return;
                    }
                }
                this.rouletteCroupier.addBet(
                        this.currentPlayer,
                        this.currentPlayer.makeBet(this.currentPlayer.getBetAmount(), RouletteBetType.COLONNE, choices));

                this.currentPlayer.resetBetAmount();
            } else {
                this.currentPlayer.restoreBalance();
            }
        } catch (final IllegalArgumentException e) {
            this.currentPlayer.restoreBalance();
        }
    }

    /**
     * Places a noir bet, if possible.
     */
    public void noirBet() {
        try {
            this.rouletteCroupier.addBet(
                    this.currentPlayer,
                    this.currentPlayer.makeBet(
                            this.currentPlayer.getBetAmount(), RouletteBetType.NOIR, Collections.emptySet()));

            this.currentPlayer.resetBetAmount();
        } catch (final IllegalArgumentException e) {
            this.currentPlayer.restoreBalance();
        }
    }

    /**
     * Places a rouge bet, if possible.
     */
    public void rougeBet() {
        try {
            this.rouletteCroupier.addBet(
                    this.currentPlayer,
                    this.currentPlayer.makeBet(
                            this.currentPlayer.getBetAmount(), RouletteBetType.ROUGE, Collections.emptySet()));

            this.currentPlayer.resetBetAmount();
        } catch (final IllegalArgumentException e) {
            this.currentPlayer.restoreBalance();
        }
    }

    /**
     * Places a pair bet, if possible.
     */
    public void pairBet() {
        try {
            this.rouletteCroupier.addBet(
                    this.currentPlayer,
                    this.currentPlayer.makeBet(this.currentPlayer.getBetAmount(), RouletteBetType.PAIR, Set.of()));
            this.currentPlayer.resetBetAmount();
        } catch (final IllegalArgumentException e) {
            this.currentPlayer.restoreBalance();
        }
    }

    /**
     * Places an impair bet, if possible.
     */
    public void impairBet() {
        try {
            this.rouletteCroupier.addBet(
                    this.currentPlayer,
                    this.currentPlayer.makeBet(this.currentPlayer.getBetAmount(), RouletteBetType.IMPAIR, Set.of()));

            this.currentPlayer.resetBetAmount();
        } catch (final IllegalArgumentException e) {
            this.currentPlayer.restoreBalance();
        }
    }

    /**
     * Places a passe bet, if possible.
     */
    public void passeBet() {
        try {
            this.rouletteCroupier.addBet(
                    this.currentPlayer,
                    this.currentPlayer.makeBet(this.currentPlayer.getBetAmount(), RouletteBetType.PASSE, Set.of()));
            this.currentPlayer.resetBetAmount();
        } catch (final IllegalArgumentException e) {
            this.currentPlayer.restoreBalance();
        }
    }

    /**
     * Places a manque bet, if possible.
     */
    public void manqueBet() {
        try {
            this.rouletteCroupier.addBet(
                    this.currentPlayer,
                    this.currentPlayer.makeBet(this.currentPlayer.getBetAmount(), RouletteBetType.MANQUE, Set.of()));

            this.currentPlayer.resetBetAmount();
        } catch (final IllegalArgumentException e) {
            this.currentPlayer.restoreBalance();
        }
    }

    /**
     * Places a douzaine bet, if possible.
     * @param event the mouse click event on the corresponding douzaine bet button.
     */
    public void douzaineBet(final MouseEvent event) {
        try {
            if (event.getSource() instanceof Button button) {
                final String id = button.getId();
                final Set<Integer> choices;
                switch (id.charAt(0)) {
                    case 'p' -> choices = RouletteWheel.firstDouzaine();
                    case 'm' -> choices = RouletteWheel.secondDouzaine();
                    case 'd' -> choices = RouletteWheel.thirdDouzaine();
                    default -> {
                        this.currentPlayer.restoreBalance();
                        return;
                    }
                }
                this.rouletteCroupier.addBet(
                        this.currentPlayer,
                        this.currentPlayer.makeBet(this.currentPlayer.getBetAmount(), RouletteBetType.DOUZAINE, choices));
                this.currentPlayer.resetBetAmount();
            } else {
                this.currentPlayer.restoreBalance();
            }
        } catch (final IllegalArgumentException e) {
            this.currentPlayer.restoreBalance();
        }
    }

    /**
     * Adds the selected bet amount to the current bet amount, if possible.
     * @param amount the amount to add.
     * @return the resulting bet amount.
     */
    public Double addBetAmount(final Integer amount) {
        if (this.currentPlayer.withdraw(Double.valueOf(amount))) {
            this.currentPlayer.addBetAmount(Double.valueOf(amount));
        }
        return this.currentPlayer.getBetAmount();
    }

    /**
     * Gets current player available money.
     * @return the current player available amount of money.
     */
    public Double getPlayerBalance() {
        return this.currentPlayer.getBalance();
    }

    /**
     * Gets all bets placed in current round.
     * @return a {@link List} of {@link Pair} containing bets and corresponding owner.
     */
    public List<Pair<Player, Bet>> getBets() {
        return this.rouletteCroupier.getRoundBet();
    }
}
