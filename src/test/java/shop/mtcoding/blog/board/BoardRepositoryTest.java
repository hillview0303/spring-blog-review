package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
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
    private EntityManager em;

    @Test
    public void updateById_test(){
        int id = 1;
        String title = "title1";
        String content = "content1";
        boardRepository.updateById(id, title, content);
        em.flush();
    }

    @Test
    public void deleteById_test(){
        int id = 1;
        boardRepository.deleteById(id);
        System.out.println("deleteById_test : "+boardRepository.findAll().size());
    }

    @Test
    public void findAllV2_test(){
        List<Board> boardList = boardRepository.findAllV2();
        System.out.println("findAllV2_test : 조회완료 쿼리 2번");
        boardList.forEach(board -> {
            System.out.println(board);
        });
    }

    @Test
    public void randomquery_test(){
        int[] ids = {1,2};

        String q = "select u from User u where u.id in (";
        for (int i=0; i<ids.length; i++){
            if(i==ids.length-1){
                q = q + "?)";
            }else{
                q = q + "?,";
            }
        }
    }

    @Test
    public void findAll_custom_inquery_test() {
        List<Board> boardList = boardRepository.findAll();

        int[] userIds = boardList.stream().mapToInt(board -> board.getUser().getId()).distinct().toArray();
        for (int i : userIds){
            System.out.println(i);
        }
    }

    @Test
    public void findAll_lazyloading_test() {
        boardRepository.findAll();
    }

    @Test
    public void findAll_test() {
        boardRepository.findAll();
    }

    @Test
    public void findByIdJoinUser_test(){
        int id = 1;
        boardRepository.findByIdJoinUser(id);
    }

    @Test
    public void findById_test() {
        int id = 1;
        boardRepository.findById(id);
    }
}