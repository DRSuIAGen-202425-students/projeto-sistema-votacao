package repository;

import model.Vote;
import java.util.*;

public class VoteRepository {
    private List<Vote> votes = new ArrayList<>();

    public void addVote(Vote vote) {
        votes.add(vote);
    }

    public int countVotesForCandidate(String candidateId) {
        return (int) votes.stream()
                .filter(v -> v.getCandidateId().equals(candidateId))
                .count();
    }

    public int totalVotes() {
        return votes.size();
    }

    public List<Vote> getAllVotes() {
        return new ArrayList<>(votes);
    }
}
