package repository;

import model.Elector;
import java.util.*;

public class ElectorRepository {
    private Map<String, Elector> electors = new HashMap<>();

    public boolean addElector(Elector elector) {
        if (electors.containsKey(elector.getUsername())) return false;
        electors.put(elector.getUsername(), elector);
        return true;
    }

    public boolean editElector(String username, String newPassword) {
        Elector elector = electors.get(username);
        if (elector != null) {
            elector.setPassword(newPassword);
            return true;
        }
        return false;
    }

    public boolean removeElector(String username) {
        return electors.remove(username) != null;
    }

    public Elector getElector(String username) {
        return electors.get(username);
    }

    public List<Elector> listElectors() {
        return new ArrayList<>(electors.values());
    }
}
