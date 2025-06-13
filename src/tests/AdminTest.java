package tests;

import model.Admin;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import security.PasswordHasher;

public class AdminTest {

    @Test
    public void testAdminConstructorAndRole() {
        String plainPassword = "admin123";
        String hashed = PasswordHasher.hashPassword(plainPassword);
        Admin admin = new Admin("adminUser", hashed);

        assertEquals("adminUser", admin.getUsername());
        assertEquals("ADMIN", admin.getRole());
        assertTrue(admin.validatePassword(plainPassword));
        assertFalse(admin.validatePassword("wrongPassword"));
    }
}
