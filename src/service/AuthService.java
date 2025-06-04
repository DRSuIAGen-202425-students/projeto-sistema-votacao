package service;

import repository.UserRepository;
import model.User;

public class AuthService {
    private UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String username, String password) {
        User user = userRepository.getUser(username);
        if (user != null && user.authenticate(password)) {
            return user;
        }
        return null;
    }
}
