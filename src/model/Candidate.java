package model;

public class Candidate {
    private String id;
    private String name;
    private String party;

    public Candidate(String id, String name, String party) {
        this.id = id;
        this.name = name;
        this.party = party;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getParty() {
        return party;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParty(String party) {
        this.party = party;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + name + " | Partido: " + party;
    }
}
