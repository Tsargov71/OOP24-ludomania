package database.schemas;

import org.json.JSONObject;

import database.schemas.api.Entry;

public class WalletEntry implements Entry {
    private final double amount;
    private final String username;

    public WalletEntry(final String username, final double amount) {
        this.amount = amount;
        this.username = username;
    }    

    public WalletEntry(final String username) {
        this(username, 0);
    }

    @Override
    public JSONObject toJson() {
        JSONObject jobj = new JSONObject();
        jobj.put("username", this.username);
        jobj.put("amoutn", this.amount);

        return jobj;
    }
}
