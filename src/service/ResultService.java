package service;

import model.Candidate;
import model.Vote;
import repository.CandidateRepository;
import repository.VoteRepository;

import java.util.*;

public class ResultService {
    private VoteRepository voteRepo;
    private CandidateRepository candidateRepo;
    private VotingSession session;

    public ResultService(VoteRepository voteRepo, CandidateRepository candidateRepo, VotingSession session) {
        this.voteRepo = voteRepo;
        this.candidateRepo = candidateRepo;
        this.session = session;
    }

    public void showResults(String adminUsername) {
        if (session.isVotingOpen()) {
            System.out.println("❌ A votação ainda está em curso. Encerramento necessário.");
            return;
        }

        List<Vote> votes = voteRepo.getAllVotes();
        int totalVotos = votes.size();

        if (totalVotos == 0) {
            System.out.println("⚠️ Nenhum voto foi registado.");
            return;
        }

        Map<String, Integer> contagem = new HashMap<>();

        for (Candidate c : candidateRepo.listCandidates()) {
            contagem.put(c.getId(), 0);
        }

        for (Vote v : votes) {
            contagem.put(v.getCandidateId(), contagem.getOrDefault(v.getCandidateId(), 0) + 1);
        }

        System.out.println("📊 Resultados da Votação:");
        Candidate vencedor = null;
        int maxVotos = -1;

        for (Candidate c : candidateRepo.listCandidates()) {
            int votos = contagem.getOrDefault(c.getId(), 0);
            double percent = (votos * 100.0) / totalVotos;
            System.out.printf("🗳️ %s (%s) - %d votos (%.2f%%)\n", c.getName(), c.getParty(), votos, percent);

            if (votos > maxVotos) {
                maxVotos = votos;
                vencedor = c;
            }
        }

        if (vencedor != null) {
            System.out.println("🏆 Vencedor: " + vencedor.getName() + " (" + vencedor.getParty() + ")");
        }
    }
}
