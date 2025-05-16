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
    public JSONObject toJson() {
        JSONObject jobj = new JSONObject();
        jobj.put("username", this.username);
        jobj.put("password", this.password);

        return jobj;
    }
}
