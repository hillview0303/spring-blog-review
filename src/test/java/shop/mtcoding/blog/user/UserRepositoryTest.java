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
    @Autowired
    private EntityManager em;

    @Test
    public void updateById_test() {
        int id = 1;
        String username = "ssar2";
        String password = "12345";
        String email = "sssar@nate.com";
        userRepository.updateById(id, username, password, email);
        em.flush();
    }

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
