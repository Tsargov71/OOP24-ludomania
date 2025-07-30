package ludomania.view;

import java.io.IOException;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import ludomania.controller.RouletteController;

/**
 * Builds and manages the JavaFX view for the Roulette game.
 * Provides UI elements for chip selection, card visualization, bet zones,
 * and balance and bet tracking.
 */
public final class RouletteViewBuilder implements ViewBuilder {
//    private static final String SEP = File.separator;
    private static final String FXML_FILE_NAME = "RouletteViewTemplate.fxml";
    private static final String FXML_STYLE_FILE_NAME = "RouletteView.css";
    private static final String FXML_FILE_PATH = FXML_FILE_NAME;
    private static final String FXML_STYLE_FILE_PATH = FXML_STYLE_FILE_NAME;

    private final RouletteController controller;

    /**
     * Creates the Roulette view builder.
     * @param controller the controller that will be bound to the UI.
     */
    public RouletteViewBuilder(final RouletteController controller) {
        this.controller = controller;
    }

    /**
     * {@inheritDoc}
     * @return the roulette game scene root.
     */
    @Override
    public Parent build() {
        BorderPane root;

        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setController(this.controller);
            loader.setLocation(ClassLoader.getSystemResource(FXML_FILE_PATH));
            root = loader.load();
            root.getStylesheets().add(ClassLoader.getSystemResource(FXML_STYLE_FILE_PATH).toString());
        } catch (final IOException e) {
            root = new BorderPane();
            root.setCenter(new Label("ERRORE DURANTE IL CARICAMENTO DELLA VIEW"));
        }

        return root;
    }
}
