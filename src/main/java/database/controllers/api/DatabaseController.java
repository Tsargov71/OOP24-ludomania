package database.controllers.api;

import java.util.List;
import java.util.Optional;

import database.models.api.DBModel;
import database.schemas.api.Entry;

/**
 * Defines the database controller interface for reading and writing on the database.
 * <p>
 * Implementations of this interface are responsible for calling the correct database model
 */
public abstract class DatabaseController {
    protected DBModel model;

    /**
     * Calls the DBModel exists method.
     * @return true if present
     */
    public boolean exists() {
        return this.model.exists();
    }

    /**
     * Calls the DBModel insert method.
     * @return true if success
     */
    public boolean insert() {
        return this.model.write();
    }

    /**
     * Calls the DBModel delete method.
     * @return true if success
     */
    public boolean delete() {
        return this.model.delete();
    }

    /**
     * Calls the DBModel read method.
     * @return the database entry if present
     */
    public <T extends Entry> Optional<T> read() {
        return this.model.read();
    }

    /**
     * Calls the DBModel readAll method.
     * @return the database entries
     */
    public <T extends Entry> Optional<List<T>> readAll() {
        return this.model.readAll();
    }
}