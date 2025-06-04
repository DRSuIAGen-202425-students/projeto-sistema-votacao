package model;

public class Vote {
    private String electorUsername;
    private String candidateId;

    public Vote(String electorUsername, String candidateId) {
        this.electorUsername = electorUsername;
        this.candidateId = candidateId;
    }

    public String getElectorUsername() {
        return electorUsername;
    }

    public String getCandidateId() {
        return candidateId;
    }
}
