package model;

public class Vote {
    private String candidateId;

    public Vote(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getCandidateId() {
        return candidateId;
    }
}
