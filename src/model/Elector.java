package model;

import security.PasswordHasher;

public class Elector extends User {
    private boolean hasVoted;

    public Elector(String username, String passwordHash) {
        super(username, passwordHash);
        this.hasVoted = false;
    }

    @Override
    public String getRole() {
        return "ELECTOR";
    }

    public boolean hasVoted() {
        return hasVoted;
    }

    public void setPassword(String plainPassword) {
        this.passwordHash = PasswordHasher.hashPassword(plainPassword);
    }

    public void setHasVoted(boolean hasVoted) {
        if (this.hasVoted && !hasVoted) {
            throw new IllegalStateException("Não é possível remover marcação de voto.");
        }
        this.hasVoted = hasVoted;
    }
}
