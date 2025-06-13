package model;

import security.PasswordHasher;

public abstract class User {
    private String username;
    // private String password;
    protected String passwordHash;  // alteração para hash

    public User(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    /*public boolean authenticate(String password) {
        return this.password.equals(password);
    }*/

    public boolean validatePassword(String passwordPlain) {
        return PasswordHasher.verifyPassword(passwordPlain, this.passwordHash);
    }

    public abstract String getRole();
}
