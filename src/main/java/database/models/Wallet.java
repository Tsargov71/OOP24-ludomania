package database.models;

import java.util.List;
import java.util.Optional;

import database.core.LudomaniaDBManager;
import database.models.api.DBModel;
import database.schemas.WalletEntry;

public class Wallet implements DBModel {
    private final String dbFilename = "wallets.json";
    private final WalletEntry entry;

    public Wallet(final WalletEntry entry) {
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
    public Optional<WalletEntry> read() {
        return (Optional<WalletEntry>)LudomaniaDBManager.getManager().read(entry, this.dbFilename);
    }

    @Override
    public Optional<List<WalletEntry>> readAll() {
        return LudomaniaDBManager.getManager().readAll(this.dbFilename);
    }
}
