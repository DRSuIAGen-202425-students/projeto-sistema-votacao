package tests;

import model.Elector;
import model.User;
import org.junit.jupiter.api.Test;
import repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryTest {

    @Test
    public void testAddAndGetUser() {
        UserRepository repo = new UserRepository();
        User user = new Elector("e1", "pass") {};
        repo.addUser(user);

        assertEquals(user, repo.getUser("e1"));
        assertNull(repo.getUser("e2"));
    }
}
