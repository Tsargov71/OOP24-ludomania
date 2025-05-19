package database.models;

import java.util.List;
import java.util.Optional;

import database.core.LudomaniaDBManager;
import database.models.api.DBModel;
import database.schemas.UserEntry;

public class User implements DBModel {
    private final String dbFilename = "users.json";
    private final UserEntry entry;

    public User(final UserEntry entry) {
        this.entry = entry;
    }

    @Override
    public boolean exists() {
        return LudomaniaDBManager.getManager().exists(entry, this.dbFilename);
    }

    @Override
    public boolean write() {
        return LudomaniaDBManager.getManager().write(entry, this.dbFilename);
    }

    @Override
    public boolean delete() {
        return LudomaniaDBManager.getManager().delete(entry, this.dbFilename);
    }

    @Override
    public Optional<UserEntry> read() {
        return (Optional<UserEntry>)LudomaniaDBManager.getManager().read(entry, this.dbFilename);
    }

    @Override
    public Optional<List<UserEntry>> readAll() {
        return LudomaniaDBManager.getManager().readAll(this.dbFilename);
    }
}
