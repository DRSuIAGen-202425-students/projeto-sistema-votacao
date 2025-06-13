import model.*;
import repository.*;
import security.PasswordHasher;
import service.*;

public class Main {
    public static void main(String[] args) {
        // Setup
        UserRepository userRepo = new UserRepository();
        CandidateRepository candidateRepo = new CandidateRepository();
        VoteRepository voteRepo = new VoteRepository();
        VotingSession session = new VotingSession();
        AuthService authService = new AuthService(userRepo);
        VotingService votingService = new VotingService(candidateRepo, userRepo, voteRepo, session);

        // Admin e eleitores
        Admin admin = new Admin("admin1", PasswordHasher.hashPassword("adminpass"));
        Elector e1 = new Elector("user1", PasswordHasher.hashPassword("pass1"));

        userRepo.addUser(admin);
        userRepo.addUser(e1);

        // Candidatos
        candidateRepo.addCandidate(new Candidate("C1", "Ana", "Partido X"));
        candidateRepo.addCandidate(new Candidate("C2", "Bruno", "Partido Y"));

        // Início da votação
        session.startVoting(admin.getUsername());

        // Login e votação
        User u = authService.login("user1", "pass1");
        if (u instanceof Elector) {
            Elector elector = (Elector) u;

            // Mostrar candidatos
            System.out.println("📋 Candidatos:");
            for (Candidate c : candidateRepo.listCandidates()) {
                System.out.println(c);
            }

            // Votar
            votingService.vote(elector.getUsername(), "C2");
            // Tentativa dupla
            votingService.vote(elector.getUsername(), "C1");
        }

        // Simular tentativa de votar 2 vezes
        Elector eleitor = (Elector) authService.login("user1", "pass1");
        votingService.vote(eleitor.getUsername(), "C1"); // Deve aceitar
        votingService.vote(eleitor.getUsername(), "C2"); // Deve recusar

        // Encerrar votação
        session.endVoting(admin.getUsername());

        // Resultados
        ResultService resultService = new ResultService(voteRepo, candidateRepo, session);
        resultService.showResults(admin.getUsername());

    }
}