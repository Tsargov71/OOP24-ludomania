package ludomania.controller.impl;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.Parent;
import javafx.util.Builder;
import ludomania.controller.api.Controller;
import ludomania.core.api.AudioManager;
import ludomania.core.api.SceneManager;
import ludomania.handler.TrenteEtQuaranteHandler;
import ludomania.view.TrenteEtQuaranteViewBuilder;

public class TrenteEtQuaranteController implements Controller, TrenteEtQuaranteHandler{
    private final Builder<Parent> viewBuilder;
    private final SceneManager sceneManager;
    private final AudioManager audioManager;
    public TrenteEtQuaranteController(final SceneManager sceneManager,
            final AudioManager audioManager) {
        this.sceneManager = sceneManager;
        this.audioManager = audioManager;
        viewBuilder = new TrenteEtQuaranteViewBuilder(this, sceneManager.getLanguageManager(),
        sceneManager.getImageProvider());
        
    }

    @Override
    public Parent getView() {
        return viewBuilder.build();
    }
    
}
