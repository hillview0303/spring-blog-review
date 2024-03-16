package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(UserRepository.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    private EntityManager em;

    @Test
    public void findById_test() {
        int id = 1;
        userRepository.findById(id);
    }

    @Test
    public void findByUsername_test() {

        UserRequest.LoginDTO reqDTO = new UserRequest.LoginDTO();
        reqDTO.setUsername("ssar");
        reqDTO.setPassword("1234");

        User user = userRepository.findByUsernameAndPassword(reqDTO);

        Assertions.assertThat(user.getUsername()).isEqualTo("ssar");
    }
}
