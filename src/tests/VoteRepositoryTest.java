package tests;

import model.Vote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.VoteRepository;

import static org.junit.jupiter.api.Assertions.*;

public class VoteRepositoryTest {
    private VoteRepository repo;

    @BeforeEach
    void setup() {
        repo = new VoteRepository();
    }

    @Test
    void testAddVoteAndCount() {
        repo.addVote(new Vote("C1"));
        repo.addVote(new Vote("C2"));
        repo.addVote(new Vote("C1"));

        assertEquals(3, repo.totalVotes());
        assertEquals(2, repo.countVotesForCandidate("C1"));
        assertEquals(1, repo.countVotesForCandidate("C2"));
    }

    @Test
    void testGetAllVotes() {
        repo.addVote(new Vote("C1"));
        assertEquals(1, repo.getAllVotes().size());
    }
}
