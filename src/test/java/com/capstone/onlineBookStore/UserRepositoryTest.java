package com.capstone.onlineBookStore;
import com.capstone.onlineBookStore.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import com.capstone.onlineBookStore.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByEmail() {
        //given
        User user = new User();
        user.setName("Test User");
        user.setEmail("test.user@test.com");
        user.setPassword("test1234");

        entityManager.persist(user);
        entityManager.flush();

        //when
        User found = userRepository.findByEmail(user.getEmail());

        //then
        assertThat(found.getEmail())
                .isEqualTo(user.getEmail());
    }
}
