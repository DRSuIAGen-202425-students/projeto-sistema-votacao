package repository;

import model.Candidate;

import java.util.*;

public class CandidateRepository {
    private Map<String, Candidate> candidates = new HashMap<>();

    public boolean addCandidate(Candidate candidate) {
        if (candidates.containsKey(candidate.getId())) return false;
        candidates.put(candidate.getId(), candidate);
        return true;
    }

    public boolean editCandidate(String id, String newName, String newParty) {
        Candidate candidate = candidates.get(id);
        if (candidate != null) {
            candidate.setName(newName);
            candidate.setParty(newParty);
            return true;
        }
        return false;
    }

    public boolean removeCandidate(String id) {
        return candidates.remove(id) != null;
    }

    public List<Candidate> listCandidates() {
        return new ArrayList<>(candidates.values());
    }

    public boolean exists(String id) {
        return candidates.containsKey(id);
    }

    public Candidate getCandidate(String id) {
        return candidates.get(id);
    }

}
