package tests;

import model.Elector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.ElectorRepository;

import static org.junit.jupiter.api.Assertions.*;

public class ElectorRepositoryTest {
    private ElectorRepository repo;
    private Elector e1;

    @BeforeEach
    void setup() {
        repo = new ElectorRepository();
        e1 = new Elector("e1", "pass");
    }

    @Test
    void testAddElector() {
        assertTrue(repo.addElector(e1));
        assertFalse(repo.addElector(e1)); // Já existe
    }

    @Test
    void testEditElector() {
        repo.addElector(e1);
        assertTrue(repo.editElector("e1", "nova"));
        assertFalse(repo.editElector("naoexiste", "123"));
    }

    @Test
    void testRemoveElector() {
        repo.addElector(e1);
        assertTrue(repo.removeElector("e1"));
        assertFalse(repo.removeElector("inexistente"));
    }

    @Test
    void testGetElector() {
        repo.addElector(e1);
        assertEquals(e1, repo.getElector("e1"));
    }

    @Test
    void testListElectors() {
        repo.addElector(e1);
        assertEquals(1, repo.listElectors().size());
    }
}
