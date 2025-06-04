package repository;

import model.Vote;
import java.util.*;

public class VoteRepository {
    private List<Vote> votes = new ArrayList<>();

    public void addVote(Vote vote) {
        votes.add(vote);
    }

    public boolean hasVoted(String electorUsername) {
        return votes.stream()
                .anyMatch(v -> v.getElectorUsername().equals(electorUsername));
    }

    public List<Vote> getAllVotes() {
        return votes;
    }

}
