package ludomania.controller;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import ludomania.controller.api.Controller;
import ludomania.core.api.AudioManager;
import ludomania.core.api.SceneManager;
import ludomania.model.Pair;
import ludomania.model.croupier.roulette.RouletteColor;
import ludomania.model.game.roulette.RouletteGame;
import ludomania.view.RouletteViewBuilder;

/**
 * Controller implementation for the Roulette game.
 * <p>
 * Manages interactions between the view (UI) and the model logic facade {@link RouletteGame}.
 */
@SuppressWarnings({"PMD.UnusedLocalVariable", "PMD.UnusedPrivateMethod"})
public class RouletteController implements Controller {

    @FXML
    private Button okBtn;

    @FXML
    private Label resultLabel;

    @FXML
    private Label totalLabel;

    @FXML
    private Label betAmountLabel;

    @FXML
    private ImageView wheel;

    @FXML
    private HBox ficheBox;

    @FXML
    private GridPane board;

    @FXML
    private Button exitBtn;

    @FXML
    private Button rulesBtn;

    @FXML
    private Button betsBtn;

    private final String betsBtnText;
    private final String rulesBtnText;

    private final RouletteGame game;
    private final RouletteViewBuilder viewBuilder;

    private final BooleanProperty okBtnDisabled = new SimpleBooleanProperty(true);
    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty total = new SimpleStringProperty();
    private final StringProperty bet = new SimpleStringProperty("0 $");
    private final IntegerProperty betAmount = new SimpleIntegerProperty();

    /**
     * Instantiate the game manager {@link RouletteGame} and the view builder {@link RouletteViewBuilder}.
     * @param sceneManager the manager for the application scenes.
     * @param audioManager the manager for audio features.
     */
    public RouletteController(final SceneManager sceneManager, final AudioManager audioManager) {
        this.game = new RouletteGame(sceneManager, audioManager);
        this.viewBuilder = new RouletteViewBuilder(this);

        this.rulesBtnText = sceneManager.getLanguageManager().getString("rule_button");
        this.betsBtnText = sceneManager.getLanguageManager().getString("bets");
    }

    /**
     * Sets starting configurations right after UI is loaded.
     */
    @FXML
    public void initialize() {
        this.okBtn.disableProperty().bind(this.okBtnDisabled);
        this.resultLabel.textProperty().bind(this.result);
        this.totalLabel.textProperty().bind(this.total);
        this.betAmountLabel.textProperty().bind(this.bet);
        this.wheel.disableProperty().bind(this.okBtnDisabled.not());
        this.board.disableProperty().bind(this.okBtnDisabled.not());
        this.ficheBox.disableProperty().bind(this.okBtnDisabled.not());
        this.exitBtn.disableProperty().bind(this.okBtnDisabled.not());

        this.resultLabel.textProperty()
                .addListener((observable, oldValue, newValue) -> okBtnDisabled.set(newValue.isBlank()));

        this.attachFiches(this.ficheBox, this.betAmount);

        this.betAmount.addListener((observable, oldValue, newValue) -> {
            final Double betAmount = this.selectAmount(newValue.intValue());
            this.bet.set(betAmount.intValue() + " $");
            this.total.set(this.getBalance().intValue() + " $");
        });

        this.total.set(this.getBalance().intValue() + " $");

        this.rulesBtn.setText(this.rulesBtnText);
        this.betsBtn.setText(this.betsBtnText);
    }

    /**
     * {@inheritDoc}
     * @return root node of the Roulette scene.
     */
    @Override
    public Parent getView() {
        return this.viewBuilder.build();
    }

    @FXML
    private void pleinBet(final MouseEvent event) {
        this.game.pleinBet(event);
        this.resetBelLabel();
    }

    @FXML
    private void chevalBet(final MouseEvent event) {
        this.game.chevalBet(event);
        this.resetBelLabel();
    }

    @FXML
    private void carreBet(final MouseEvent event) {
        this.game.carreBet(event);
        this.resetBelLabel();
    }

    @FXML
    private void colonneBet(final MouseEvent event) {
        this.game.colonneBet(event);
        this.resetBelLabel();
    }

    @FXML
    private void noirBet() {
        this.game.noirBet();
        this.resetBelLabel();
    }

    @FXML
    private void rougeBet() {
        this.game.rougeBet();
        this.resetBelLabel();
    }

    @FXML
    private void pairBet() {
        this.game.pairBet();
        this.resetBelLabel();
    }

    @FXML
    private void impairBet() {
        this.game.impairBet();
        this.resetBelLabel();
    }

    @FXML
    private void passeBet() {
        this.game.passeBet();
        this.resetBelLabel();
    }

    @FXML
    private void manqueBet() {
        this.game.manqueBet();
    }

    @FXML
    private void douzineBet(final MouseEvent event) {
        this.game.douzineBet(event);
        this.resetBelLabel();
    }

    @FXML
    private void spinWheel(final MouseEvent event) {
        this.game.spinWheel(event);
        final Pair<Integer, RouletteColor> result = this.game.runGame().getResult();
        this.result.set(result.getKey().toString());
        final String colorStyle = "-fx-text-fill: " + this.resultColor(result.getValue()) + ";";
        this.resultLabel.setStyle(colorStyle);
    }

    @FXML
    private void highlightCarre(final MouseEvent event) {
        this.game.highlightCarre(event);
    }

    @FXML
    private void unhighlightCarre(final MouseEvent event) {
        this.game.unhighlightCarre(event);
    }

    @FXML
    private void glowWheel(final MouseEvent event) {
        this.game.glowWheel(event);
    }

    @FXML
    private void unglowWheel(final MouseEvent event) {
        this.game.unglowWheel(event);
    }

    @FXML
    private void quitGame() {
        this.game.quitGame();
    }

    @FXML
    private Double selectAmount(final Integer amount) {
        return this.game.addBetAmount(amount);
    }

    @FXML
    private void evaluateRound() {
        this.result.set("");
        this.game.evaluateRound();

        this.total.set(this.getBalance().intValue() + " $");
    }

    @FXML
    private void showRules() {
        this.game.showRules();
    }

    @FXML
    private void showBets() {
        this.game.showBets();
    }

    private String resultColor(final RouletteColor color) {
        return switch (color.name()) {
            case "NOIR" -> "black";
            case "ROUGE" -> "red";
            case "VERT" -> "green";
            default -> "white";
        };
    }

    private void attachFiches(final Pane pane, final IntegerProperty controlProperty) {
        this.game.attachFiches(pane, controlProperty);
    }

    private Double getBalance() {
        return this.game.getBalance();
    }

    private void resetBelLabel() {
        this.bet.set("0 $");
    }
}
