package service;

import model.*;
import repository.*;

public class VotingService {
    private CandidateRepository candidateRepo;
    private UserRepository userRepo;
    private VoteRepository voteRepo;
    private VotingSession votingSession;

    public VotingService(CandidateRepository candidateRepo, UserRepository userRepo, VoteRepository voteRepo, VotingSession votingSession) {
        this.candidateRepo = candidateRepo;
        this.voteRepo = voteRepo;
        this.votingSession = votingSession;
        this.userRepo = userRepo;
    }

    public boolean vote(String username, String candidateId) {
        // 1) Só se vota com sessão aberta
        if (!votingSession.isVotingOpen()) {
            System.out.println("❌ A votação não está ativa.");
            return false;
        }

        // 2) Verificar se o utilizador existe e é elector
        User user = userRepo.getUser(username);
        if (!(user instanceof Elector)) {
            System.out.println("❌ Apenas eleitores podem votar.");
            return false;
        }
        Elector elector = (Elector) user;

        // 3) Verificar se já votou
        if (elector.hasVoted()) {
            System.out.println("❌ Este eleitor já votou.");
            return false;
        }

        // 4) Verificar existência do candidato
        if (!candidateRepo.exists(candidateId)) {
            System.out.println("❌ Candidato inválido.");
            return false;
        }

        // 5) Registar voto (anónimo)
        voteRepo.addVote(new Vote(candidateId));

        // 6) Marcar o elector como já tendo votado
        elector.setHasVoted(true);

        System.out.println("✅ Voto registado de forma anónima.");
        return true;
    }
}
