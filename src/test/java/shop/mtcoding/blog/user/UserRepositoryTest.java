package shop.mtcoding.blog.user;

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

    @Test
    public void findByUsername_test() {

        UserRequest.LoginDTO reqDTO = new UserRequest.LoginDTO();
        reqDTO.setUsername("ssar");
        reqDTO.setPassword("1234");

        User user = userRepository.findByUsernameAndPassword(reqDTO);

        Assertions.assertThat(user.getUsername()).isEqualTo("ssar");
    }
}