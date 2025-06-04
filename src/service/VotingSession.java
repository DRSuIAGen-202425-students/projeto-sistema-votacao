package service;

public class VotingSession {
    private boolean started = false;

    public void startVoting(String adminUsername) {
        if (!started) {
            started = true;
            System.out.println("✅ [" + adminUsername + "] iniciou a votação.");
        } else {
            System.out.println("⚠️ A votação já foi iniciada.");
        }
    }

    public void endVoting(String adminUsername) {
        if (started) {
            started = false;
            System.out.println("✅ [" + adminUsername + "] encerrou a votação.");
        } else {
            System.out.println("⚠️ A votação já está encerrada.");
        }
    }

    public boolean isVotingOpen() {
        return started;
    }
}
