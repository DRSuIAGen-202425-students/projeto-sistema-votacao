package tests;

import model.Candidate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.CandidateRepository;

import static org.junit.jupiter.api.Assertions.*;

public class CandidateRepositoryTest {
    private CandidateRepository repo;
    private Candidate c1;

    @BeforeEach
    void setup() {
        repo = new CandidateRepository();
        c1 = new Candidate("C1", "Ana", "X");
    }

    @Test
    void testAddCandidate() {
        assertTrue(repo.addCandidate(c1));
        assertFalse(repo.addCandidate(c1)); // duplicado
    }

    @Test
    void testEditCandidate() {
        repo.addCandidate(c1);
        assertTrue(repo.editCandidate("C1", "Ana Nova", "Y"));
        assertFalse(repo.editCandidate("NAOEXISTE", "X", "Z"));
    }

    @Test
    void testRemoveCandidate() {
        repo.addCandidate(c1);
        assertTrue(repo.removeCandidate("C1"));
        assertFalse(repo.removeCandidate("NAO"));
    }

    @Test
    void testListAndExists() {
        repo.addCandidate(c1);
        assertEquals(1, repo.listCandidates().size());
        assertTrue(repo.exists("C1"));
        assertFalse(repo.exists("X"));
    }

    @Test
    void testGetCandidate() {
        repo.addCandidate(c1);
        assertEquals(c1, repo.getCandidate("C1"));
    }
}
