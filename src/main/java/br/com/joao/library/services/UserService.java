package br.com.joao.library.services;

import br.com.joao.library.domain.user.User;
import br.com.joao.library.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("ERROR: User not found"));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findUserByEmail(String email){
        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isPresent()) return user;
        throw new RuntimeException("User not found");
    }

}
