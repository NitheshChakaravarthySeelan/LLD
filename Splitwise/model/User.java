package Splitwise.model;

import java.util.UUID;

public class User {
    private final String id;
    private final String userName;

    public User(String userName, String email) {
        this.id = UUID.randomUUID().toString();
        this.userName = userName;
    }

    public String getId() {
        return this.id;
    }

    public String getUserName() {
        return this.userName;
    };
}