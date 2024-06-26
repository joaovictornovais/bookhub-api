package br.com.joao.library.services;

import br.com.joao.library.domain.user.User;
import br.com.joao.library.exceptions.EntityNotFoundException;
import br.com.joao.library.exceptions.InvalidArgumentsException;
import br.com.joao.library.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUser(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User create(User user) {
        try {
            findUserByEmail(user.getEmail());
            throw new InvalidArgumentsException("E-mail already registered");
        } catch (EntityNotFoundException e) {
            return userRepository.save(user);
        }
    }

    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email).orElseThrow(() ->
                new EntityNotFoundException("User with email '" + email + "' not found"));
    }

}
