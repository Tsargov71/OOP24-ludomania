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
    public final JSONObject toJson() {
        JSONObject j = new JSONObject();
        j.put("amount", this.amount);
        j.put("username", this.username);

        return j;
    }
}
