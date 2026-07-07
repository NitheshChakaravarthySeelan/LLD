package Splitwise.model;

import java.util.List;
import java.util.UUID;

public class Group {
    private final String id;
    private final User createdBy;
    private final List<User> users;
    private final List<Expences> expences;


    public Group(User createdBy, List<User> members, List<Expences> expences) {
        this.id = UUID.randomUUID().toString();
        this.createdBy = createdBy;
        this.users = members;
        this.expences = expences;
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

    public List<Expences> getExpences() {
        return this.expences;
    }

    public void addMembers(User newUser) {
        this.users.add(newUser);
    }

    public void removeMember(User user) {
        this.users.remove(user);
    }

    public void addExpence(Expences expences) {
        this.expences.add(expences);
    }
}
