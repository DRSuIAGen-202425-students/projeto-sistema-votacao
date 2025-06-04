package service;

import model.*;
import repository.*;

public class VotingService {
    private CandidateRepository candidateRepo;
    private VoteRepository voteRepo;
    private VotingSession votingSession;

    public VotingService(CandidateRepository candidateRepo, VoteRepository voteRepo, VotingSession votingSession) {
        this.candidateRepo = candidateRepo;
        this.voteRepo = voteRepo;
        this.votingSession = votingSession;
    }

    public boolean vote(Elector elector, String candidateId) {
        if (!votingSession.isVotingOpen()) {
            System.out.println("❌ A votação não está ativa.");
            return false;
        }

        /*if (elector.hasVoted()) {
            System.out.println("❌ Este eleitor já votou.");
            return false;
        }*/

        if (elector.hasVoted() || voteRepo.hasVoted(elector.getUsername())) {
            System.out.println("❌ Este eleitor já votou.");
            return false;
        }


        if (!candidateRepo.exists(candidateId)) {
            System.out.println("❌ Candidato inválido.");
            return false;
        }

        Vote vote = new Vote(elector.getUsername(), candidateId);
        voteRepo.addVote(vote);
        elector.setHasVoted(true);
        System.out.println("✅ Voto registado com sucesso.");
        return true;
    }
}
