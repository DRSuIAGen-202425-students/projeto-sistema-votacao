package tests;

import model.Vote;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VoteTest {

    @Test
    public void testGetCandidateId() {
        Vote vote = new Vote("cand1");
        assertEquals("cand1", vote.getCandidateId());
    }
}
