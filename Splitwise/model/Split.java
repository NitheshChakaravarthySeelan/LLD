package Splitwise.model;

import java.util.UUID;

public class Split {
    private final String id;
    private final User user;
    private final int amount;

    public Split(User user, int amount) {
        this.id = UUID.randomUUID().toString();
        this.user = user; 
        this.amount = amount;
    }

    public String getId() {
        return this.id;
    }

    public User getUser() {
        return this.user;
    }

    public int getAmount() {
        return this.amount;
    }
}