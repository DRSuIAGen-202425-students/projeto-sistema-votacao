package model;

public class Elector extends User {
    private boolean hasVoted = false;

    public Elector(String username, String password) {
        super(username, password);
    }
    public boolean hasVoted() {
        return hasVoted;
    }

    /*public void setHasVoted(boolean hasVoted) {
        this.hasVoted = hasVoted;
    }*/

    public void setHasVoted(boolean hasVoted) {
        if (this.hasVoted && !hasVoted) {
            throw new IllegalStateException("Não é possível remover marcação de voto.");
        }
        this.hasVoted = hasVoted;
    }

    @Override
    public String getRole() {
        return "ELECTOR";
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
