package Splitwise.model;

import java.util.List;
import java.util.UUID;

public class Expences {
    private final String id;
    private final Group group;
    private final User paidBy;
    private final List<Split> split;
    private final int amount;
   
    public Expences(Group group, User paidBy, List<Split> split, int amount) {
        this.id = UUID.randomUUID().toString();
        this.group = group;
        this.paidBy = paidBy;
        this.split = split;
        this.amount = amount;
    }

    public String getId() {
        return this.id;
    }

    public User getPaidBy() {
        return this.paidBy;
    }

    public Group getGroup() {
        return this.group;
    }

    public List<Split> getSplit() {
        return this.split;
    }

    public int getAmount() {
        return this.amount;
    }
}