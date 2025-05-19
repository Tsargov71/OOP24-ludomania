package database.schemas;

import org.json.JSONObject;

import database.schemas.api.Entry;

public class UserEntry implements Entry {
    private final String username;
    private final String password;

    public UserEntry(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public final JSONObject toJson() {
        final JSONObject j = new JSONObject();
        j.put("password", this.password);
        j.put("username", this.username);

        return j;
    }

    @Override
    public final String getIdentifier() {
        return this.username;
    }

    public String getUsername() {
        return this.username;
    }
    
    public String getPassword() {
        return this.password;
    }

}
