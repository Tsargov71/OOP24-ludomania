package ludomania.core;

import database.controllers.LudomaniaDBController;
import database.controllers.api.DatabaseController;
import database.schemas.UserEntry;
import database.schemas.WalletEntry;
import database.schemas.api.Entry;

/**
 * Main class for launching the Ludomania application.
 * <p>
 * This class starts the JavaFX application and sets up the various managers
 * required for the application's functionality, including settings, image,
 * audio,
 * language, and scene managers. After initialization, the main menu scene is
 * displayed.
 * </p>
 */

public final class Ludomania {
    /**
     * The main entry point for the application.
     * <p>
     * This method starts the application by calling the launch(String[])
     * method from JavaFX.
     * </p>
     * 
     * @param args command line arguments (not used in this case)
     */

    public static void main(final String[] args) {
        DatabaseController usersController = new LudomaniaDBController(new UserEntry("Paperino", "ciaobello99"));
        DatabaseController walletsController = new LudomaniaDBController(new WalletEntry("pippo", 0.0));

        usersController.insert();
        Entry usr = usersController.read();
        System.out.println(usr);
    }

}
