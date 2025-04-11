package ludomania.core;

import java.util.HashMap;

import javafx.application.Application;
import javafx.stage.Stage;
import ludomania.core.api.AudioManager;
import ludomania.core.api.ImageManager;
import ludomania.core.api.LanguageManager;
import ludomania.core.api.SceneManager;
import ludomania.core.impl.AudioManagerImpl;
import ludomania.core.impl.ImageManagerImpl;
import ludomania.core.impl.LanguageManagerImpl;
import ludomania.core.impl.SceneManagerImpl;
import ludomania.settings.api.SettingsManager;
import ludomania.settings.impl.SettingsManagerImpl;

public final class Ludomania extends Application {

    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {
        final SettingsManager settingsManager = new SettingsManagerImpl();
        final ImageManager imageManager = new ImageManagerImpl(new HashMap<>());
        imageManager.init();
        final AudioManager audioManager = new AudioManagerImpl(settingsManager.volumeProperty().doubleValue());
        audioManager.initialize();
        final LanguageManager languageManager = new LanguageManagerImpl(settingsManager.currentLocaleProperty().get());
        final SceneManager sceneManager = new SceneManagerImpl(primaryStage, settingsManager, audioManager,
                languageManager, imageManager);
        sceneManager.switchToMainMenu();
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}