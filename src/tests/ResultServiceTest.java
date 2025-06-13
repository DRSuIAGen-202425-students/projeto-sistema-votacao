package tests;

import model.Candidate;
import model.Vote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.CandidateRepository;
import repository.VoteRepository;
import service.ResultService;
import service.VotingSession;

public class ResultServiceTest {

    private VoteRepository voteRepo;
    private CandidateRepository candidateRepo;
    private VotingSession session;
    private ResultService resultService;

    @BeforeEach
    public void setup() {
        voteRepo = new VoteRepository();
        candidateRepo = new CandidateRepository();
        session = new VotingSession();
        resultService = new ResultService(voteRepo, candidateRepo, session);
    }

    @Test
    public void testShowResultsWhileVotingOpen() {
        session.startVoting("admin");
        resultService.showResults("admin"); // Deve imprimir que ainda está em curso
    }

    @Test
    public void testShowResultsWithoutVotes() {
        session.endVoting("admin");
        resultService.showResults("admin"); // Deve imprimir nenhum voto
    }

    @Test
    public void testShowResultsWithVotes() {
        session.endVoting("admin");
        candidateRepo.addCandidate(new Candidate("c1", "Nome", "Partido"));
        voteRepo.addVote(new Vote("c1"));
        resultService.showResults("admin"); // Deve imprimir resultados com 1 voto
    }
}
