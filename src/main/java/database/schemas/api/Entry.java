package database.schemas.api;

import org.json.JSONObject;

/**
 * Defines the database entry interface.
 * <p>
 * Represents a record in the database.
 */
public interface Entry {
    /**
     * Converts current entry into a JSONObject.
     * @return the corresponding JSONObject for the entry.
     */
    JSONObject toJson();

    /**
     * Getter for the entry identifier
     * @return a string identifier
     */
    String getIdentifier();
}
