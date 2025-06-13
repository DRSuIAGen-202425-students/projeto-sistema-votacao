package model;

public class Admin extends User {
    public Admin(String username, String passwordPlain) {
        super(username, passwordPlain);
    }

    @Override
    public String getRole() {
        return "ADMIN";
    }
}
