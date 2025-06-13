package tests;

import model.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import security.PasswordHasher;

public class UserTest {

    // Subclasse concreta apenas para testar User
    public static class TestUser extends User {
        public TestUser(String username, String passwordHash) {
            super(username, passwordHash);
        }

        @Override
        public String getRole() {
            return "TEST";
        }
    }

    @Test
    public void testUserFieldsAndPasswordValidation() {
        String plainPassword = "mypassword";
        String hashed = PasswordHasher.hashPassword(plainPassword);
        TestUser user = new TestUser("user1", hashed);

        assertEquals("user1", user.getUsername());
        assertEquals(hashed, user.getPasswordHash());
        assertTrue(user.validatePassword("mypassword"));
        assertFalse(user.validatePassword("wrongpass"));
    }

    @Test
    public void testUserRole() {
        TestUser user = new TestUser("user2", PasswordHasher.hashPassword("pass"));
        assertEquals("TEST", user.getRole());
    }
}
