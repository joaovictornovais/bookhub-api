package br.com.joao.library.repositories;

import br.com.joao.library.domain.user.User;
import br.com.joao.library.domain.user.UserDTO;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("Should get a User from DB")
    void findUserByEmailCase1() {
        String email = "joaovkt.novais@gmail.com";
        UserDTO data = new UserDTO(
                "João Victor",
                "Novais",
                email
        );

        this.createUser(data);

        Optional<User> result = userRepository.findUserByEmail(email);

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should not get a User from DB")
    void findUserByEmailCase2() {
        String email = "joaovkt.novais@gmail.com";

        Optional<User> result = userRepository.findUserByEmail(email);
        assertThat(result.isEmpty()).isTrue();
    }

    private User createUser(UserDTO data) {
        User newUser = new User(data);
        this.entityManager.persist(newUser);
        return newUser;
    }

}