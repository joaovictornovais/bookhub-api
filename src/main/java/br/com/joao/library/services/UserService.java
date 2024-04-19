package br.com.joao.library.services;

import br.com.joao.library.domain.user.User;
import br.com.joao.library.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

}
