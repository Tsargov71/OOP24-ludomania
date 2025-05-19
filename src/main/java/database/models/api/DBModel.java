package database.models.api;

import java.util.List;
import java.util.Optional;

import database.schemas.api.Entry;

/**
 * Defines the database model interface for reading and writing database files.
 * <p>
 * Implementations of this interface handle the operations of 
 * reading, writing, updating and deleting records from the database
 */
public interface DBModel {

    /**
     * Checks if a record is present in the database.
     */
    boolean exists();

    /**
     * Inserts a new record in the database.
     */
    boolean write();

    /**
     * Removes an existing record from the database.
     */
    boolean delete();

    /**
     * Reads a record from the database.
     */
    <T extends Entry> Optional<T> read();

    /**
     * Reads all the records in the database
     * @return an Optional of List of entries
     */
    <T extends Entry> Optional<List<T>> readAll();
}