package shop.mtcoding.blog.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(BoardRepository.class)
@DataJpaTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void findAllV2_test(){
        List<Board> boardList = boardRepository.findAllV2();
        boardList.forEach(board -> {
            System.out.println(board);
        });
    }

    @Test
    public void findAll_test() {
        List<Board> boardList = boardRepository.findAll();
    }

    @Test
    public void findByIdJoinUser_test(){
        int id = 1;

        boardRepository.findByIdJoinUser(id);
    }

    @Test
    public void findById_test() {
        int id = 1;

        Board board = boardRepository.findById(id);
    }
}
