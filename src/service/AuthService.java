package service;

import repository.UserRepository;
import model.User;

public class AuthService {
    private UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String username, String plainPassword) {
        // Aqui o LLM não conseguiu buscar o métd. "getUser" e em vez disso escreveu "findByUsername" (corrigido)
        User user = userRepository.getUser(username);
        if (user != null && user.validatePassword(plainPassword)) {
            return user;
        }
        System.out.println("❌ Credenciais inválidas.");
        return null;
    }
}
