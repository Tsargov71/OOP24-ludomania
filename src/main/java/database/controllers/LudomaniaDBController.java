package database.controllers;

import database.controllers.api.DatabaseController;
import database.models.User;
import database.models.Wallet;
import database.schemas.UserEntry;
import database.schemas.WalletEntry;

public class LudomaniaDBController extends DatabaseController {
    public LudomaniaDBController(final UserEntry entry) {
        this.model = new User(entry);
    }

    public LudomaniaDBController(final WalletEntry entry) {
        this.model = new Wallet(entry);
    }
}
