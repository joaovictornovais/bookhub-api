package br.com.joao.library.services;

import br.com.joao.library.domain.user.User;
import br.com.joao.library.exceptions.EntityNotFoundException;
import br.com.joao.library.exceptions.InvalidArgumentsException;
import br.com.joao.library.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    @InjectMocks
    UserService userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should find a user by Id")
    void findUserByIdCase1()
    {
        User data = new User(UUID.randomUUID(), "João Victor", "Novais", "joaovkt.novais@gmail.com");

        when(userRepository.findById(data.getId())).thenReturn(Optional.of(data));

        Optional<User> user = userRepository.findById(data.getId());

        assertThat(user).isNotEmpty();
        assertThat(user.get()).isEqualTo(data);
    }

    @Test
    @DisplayName("Should throw a exception when user not found")
    void findUserByIdCase2() {
        Exception thrown = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            if (userRepository.findById(UUID.randomUUID()).isEmpty())
                throw new EntityNotFoundException("User not found");
        });

        Assertions.assertEquals("User not found", thrown.getMessage());
    }

    @Test
    @DisplayName("Should return all users from DB")
    void findAllUsersCase1() {
        User user1 = new User(UUID.randomUUID(), "João Victor", "Novais", "joaovkt.novais@gmail.com");
        User user2 = new User(UUID.randomUUID(), "Matheus Henrique", "Meira", "mateus@gmail.com");

        when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        List<User> users = userRepository.findAll();

        assertThat(users).isNotNull();
        assertThat(users.size()).isEqualTo(2);
        assertThat(users).contains(user1, user2);
    }

    @Test
    @DisplayName("Should create a user successfully")
    void createUserCase1() {
        User user = new User(UUID.randomUUID(), "João Victor", "Novais", "joaovkt.novais@gmail.com");
        userRepository.save(user);
        verify(userRepository, times(1)).save(user);
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        Optional<User> response = userRepository.findById(user.getId());

        assertThat(response).isNotEmpty();
        assertThat(response.get()).isEqualTo(user);
    }

    @Test
    @DisplayName("Should throw a exception when trying to create a user with an e-mail already registered")
    void createUserCase2() {
        User user = new User(UUID.randomUUID(), "João Victor", "Novais", "joaovkt.novais@gmail.com");
        when(userRepository.findUserByEmail(user.getEmail())).thenReturn(Optional.of(user));

        User user2 = new User(UUID.randomUUID(), "João Victor", "dos Santos", "joaovkt.novais@gmail.com");

        Exception thrown = Assertions.assertThrows(InvalidArgumentsException.class, () -> {
            if (userRepository.findUserByEmail(user2.getEmail()).isPresent())
                throw new InvalidArgumentsException("E-mail already registered");
        });

        Assertions.assertEquals("E-mail already registered", thrown.getMessage());
    }

    @Test
    @DisplayName("Should find a user by e-mail")
    void findUserByEmailCase1() {
        String email = "joaovkt.novais@gmail.com";
        User user = new User(UUID.randomUUID(), "João Victor", "Novais", email);
        when(userRepository.findUserByEmail(email)).thenReturn(Optional.of(user));

        Optional<User> response = userRepository.findUserByEmail(email);

        assertThat(response).isNotEmpty();
        assertThat(response.get()).isEqualTo(user);
    }

    @Test
    @DisplayName("Should throw a exception when no users are found by email")
    void findUserByEmailCase2() {
        String email = "joaovkt.novais@gmail.com";

        Exception thrown = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            if (userRepository.findUserByEmail(email).isEmpty())
                throw new EntityNotFoundException("User with email '" + email + "' not found");
        });

        Assertions.assertEquals("User with email '" + email + "' not found", thrown.getMessage());

    }
}