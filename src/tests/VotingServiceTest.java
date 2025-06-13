package tests;

import model.Admin;
import model.Candidate;
import model.Elector;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.CandidateRepository;
import repository.UserRepository;
import repository.VoteRepository;
import service.VotingService;
import service.VotingSession;

import static org.junit.jupiter.api.Assertions.*;

public class VotingServiceTest {

    private CandidateRepository candidateRepo;
    private UserRepository userRepo;
    private VoteRepository voteRepo;
    private VotingSession session;
    private VotingService votingService;

    @BeforeEach
    public void setup() {
        candidateRepo = new CandidateRepository();
        userRepo = new UserRepository();
        voteRepo = new VoteRepository();
        session = new VotingSession();
        votingService = new VotingService(candidateRepo, userRepo, voteRepo, session);
    }

    @Test
    public void testVoteFailsWhenSessionClosed() {
        Elector e = new Elector("e1", "123");
        userRepo.addUser(e);
        candidateRepo.addCandidate(new Candidate("c1", "Nome", "Partido"));

        boolean result = votingService.vote("e1", "c1");
        assertFalse(result);
    }

    @Test
    public void testVoteFailsWhenUserNotElector() {
        session.startVoting("admin");
        User u = new Admin("a1", "pass") {};
        userRepo.addUser(u);

        boolean result = votingService.vote("u1", "c1");
        assertFalse(result);
    }

    @Test
    public void testVoteFailsIfAlreadyVoted() {
        session.startVoting("admin");
        Elector e = new Elector("e1", "123");
        e.setHasVoted(true);
        userRepo.addUser(e);

        boolean result = votingService.vote("e1", "c1");
        assertFalse(result);
    }

    @Test
    public void testVoteFailsIfCandidateNotExists() {
        session.startVoting("admin");
        Elector e = new Elector("e1", "123");
        userRepo.addUser(e);

        boolean result = votingService.vote("e1", "x");
        assertFalse(result);
    }

    @Test
    public void testVoteSuccess() {
        session.startVoting("admin");
        Elector e = new Elector("e1", "123");
        userRepo.addUser(e);
        candidateRepo.addCandidate(new Candidate("c1", "Nome", "Partido"));

        boolean result = votingService.vote("e1", "c1");
        assertTrue(result);
        assertTrue(e.hasVoted());
        assertEquals(1, voteRepo.totalVotes());
    }
}
