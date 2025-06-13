package tests;

import model.Elector;
import org.junit.jupiter.api.Test;
import security.PasswordHasher;

import static org.junit.jupiter.api.Assertions.*;

public class ElectorTest {

    @Test
    void testElectorConstructorAndGetters() {
        Elector e = new Elector("user1", "password");
        assertEquals("user1", e.getUsername());
        assertFalse(e.hasVoted());
    }

    @Test
    void testSetPasswordAndValidate() {
        Elector e = new Elector("user", PasswordHasher.hashPassword("1234"));
        assertTrue(e.validatePassword("1234"));
        e.setPassword("4321");
        assertTrue(e.validatePassword("4321"));
        assertFalse(e.validatePassword("1234"));
    }

    @Test
    void testSetHasVoted() {
        Elector e = new Elector("x", "pass");
        assertFalse(e.hasVoted());
        e.setHasVoted(true);
        assertTrue(e.hasVoted());
    }
}
