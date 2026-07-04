package Splitwise.model;

import java.util.List;
import java.util.UUID;

public class Group {
    private final String id;
    private final User createdBy;
    private final List<User> users;


    public Group(User createdBy, List<User> members) {
        this.id = UUID.randomUUID().toString();
        this.createdBy = createdBy;
        this.users = members;
    }

    public String getId() {
        return this.id;
    }

    public List<User> getMemebrs() {
        return this.users;
    }

    public User getOwner() {
        return this.createdBy;
    }

}