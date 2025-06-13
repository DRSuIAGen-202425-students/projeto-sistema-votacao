package tests;

import model.Elector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.UserRepository;
import security.PasswordHasher;
import service.AuthService;

import static org.junit.jupiter.api.Assertions.*;

public class AuthServiceTest {
    private AuthService authService;
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        userRepository = new UserRepository();
        authService = new AuthService(userRepository);

        userRepository.addUser(new Elector("user1", PasswordHasher.hashPassword("pass1")));
        userRepository.addUser(new Elector("user2", PasswordHasher.hashPassword("pass2")));
    }

    @Test
    void testLoginSuccess() {
        assertNotNull(authService.login("user1", "pass1"));
    }

    @Test
    void testLoginFailWrongPassword() {
        assertNull(authService.login("user1", "wrongpass"));
    }

    @Test
    void testLoginFailNoUser() {
        assertNull(authService.login("noone", "pass"));
    }
}
