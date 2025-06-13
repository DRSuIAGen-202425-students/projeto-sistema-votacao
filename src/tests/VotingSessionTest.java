package tests;

import org.junit.jupiter.api.Test;
import service.VotingSession;

import static org.junit.jupiter.api.Assertions.*;

public class VotingSessionTest {

    @Test
    public void testStartAndEndVoting() {
        VotingSession session = new VotingSession();
        assertFalse(session.isVotingOpen());

        session.startVoting("admin");
        assertTrue(session.isVotingOpen());

        session.startVoting("admin"); // já iniciada

        session.endVoting("admin");
        assertFalse(session.isVotingOpen());

        session.endVoting("admin"); // já encerrada
    }
}
