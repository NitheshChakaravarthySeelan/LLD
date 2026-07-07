package Splitwise.model;

import java.util.UUID;

public class Split {
    private final String id;
    private final User user;
    private final double amount;

    public Split(User user, double each) {
        this.id = UUID.randomUUID().toString();
        this.user = user; 
        this.amount = each;
    }

    public String getId() {
        return this.id;
    }

    public User getUser() {
        return this.user;
    }

    public double getAmount() {
        return this.amount;
    }
}