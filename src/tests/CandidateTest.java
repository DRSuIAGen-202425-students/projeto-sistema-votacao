package tests;

import model.Candidate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CandidateTest {

    @Test
    void testCandidateGettersSetters() {
        Candidate c = new Candidate("id1", "Ana", "Party1");
        assertEquals("id1", c.getId());
        assertEquals("Ana", c.getName());
        assertEquals("Party1", c.getParty());

        c.setName("Maria");
        c.setParty("Party2");

        assertEquals("Maria", c.getName());
        assertEquals("Party2", c.getParty());
    }
}
