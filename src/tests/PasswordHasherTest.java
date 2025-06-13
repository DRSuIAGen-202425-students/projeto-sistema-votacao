package tests;

import org.junit.jupiter.api.Test;
import security.PasswordHasher;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordHasherTest {

    @Test
    public void testHashAndVerifyPassword() {
        String password = "securePass!";
        String hash = PasswordHasher.hashPassword(password);

        assertNotNull(hash);
        assertEquals(64, hash.length()); // SHA-256 produz 64 caracteres hexadecimais
        assertTrue(PasswordHasher.verifyPassword(password, hash));
        assertFalse(PasswordHasher.verifyPassword("wrongPassword", hash));
    }

    @Test
    public void testHashConsistency() {
        String password = "repeatable";
        String hash1 = PasswordHasher.hashPassword(password);
        String hash2 = PasswordHasher.hashPassword(password);

        assertEquals(hash1, hash2);
    }

    @Test
    public void testDifferentPasswordsProduceDifferentHashes() {
        String hash1 = PasswordHasher.hashPassword("pass1");
        String hash2 = PasswordHasher.hashPassword("pass2");

        assertNotEquals(hash1, hash2);
    }
}
