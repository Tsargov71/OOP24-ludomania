package database.core.api;

import java.util.List;
import java.util.Optional;

import database.schemas.api.Entry;

/**
 * Defines the database manager interface for reading and writing database files.
 * <p>
 * Implementations of this interface handle the operations of 
 * reading, writing, updating and deleting records from the database
 */
public interface DBManager {
    /**
     * Stores or updates, if already present, a new record in the database.
     * @param entry the database entry to store
     * @param filename the database file in which to store the entry
     * @return true if success
     */
    <T extends Entry> boolean write(T entry, String filename);
    
    /**
     * Removes a record from the database.
     * @param entry the database entry to remove
     * @param filename the database file from which to remove the entry
     * @return true if success
     */
    boolean delete(Entry entry, String filename);

    /**
     * Reads a record from the database if present.
     * @param entry entry to read from the database
     * @param filename file in the database from which retriving the record
     * @return an optional of Entry
     */
    <T extends Entry> Optional<T> read(T entry, final String filename);

    /**
     * Read all the records from a database file.
     * @param filename name of the database file from which to read
     * @return a list of all the records
     */
    <T extends Entry> Optional<List<T>> readAll(String filename);

    /**
     * Tells if the given entry is stored in the database
     * @param entry the entry to check if present
     * @param filename the name of the database file in which to search
     * @return true if the record is present in the database
     */
    boolean exists(Entry entry, String filename);
}
