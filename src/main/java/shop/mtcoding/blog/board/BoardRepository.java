package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    @Transactional
    public Board updateById(int id, String title, String content){
        Board board = findById(id);
        board.setTitle(title);
        board.setContent(content);
        return board;
    }

    @Transactional
    public void deleteById(int id){
        Query query = em.createQuery("delete from Board b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Transactional
    public void save(Board board){
        em.persist(board);
    }

    public List<Board> findAllV3(){
        String q1 = "select b from Board b order by b.id desc";
        List<Board> boardList = em.createQuery(q1 , Board.class).getResultList();

        int[] userIds = boardList.stream().mapToInt(board -> board.getUser().getId()).distinct().toArray();

        String q2 = "select u from User u where u.id in (";
        for (int i = 0; i < userIds.length ; i++) {
            if (i == userIds.length - 1){
                q2 = q2 + userIds[i] + ")";
            }else {
                q2 = q2 + userIds[i] + ",";
            }
        }
        List<User> userList = em.createQuery(q2 , User.class).getResultList();

        for (Board board : boardList){
            for (int i = 0; i < userList.size(); i++) {
                User user = userList.get(i);
                if (user.getId() == board.getUser().getId()){
                    board.setUser(user);
                }
            }
        }
        return boardList; // user가 채워져 있어야함.
    }

    public List<Board> findAllV2(){
        Query q1 = em.createQuery("select b from Board b order by b.id desc", Board.class);
        List<Board> boardList = q1.getResultList();

        List<Integer> userIds = boardList.stream().mapToInt(value -> value.getUser().getId()).distinct().boxed().toList();


        Query q2 = em.createQuery("select u from User u where u.id in :userIds", User.class);
        q2.setParameter("userIds", userIds);
        List<User> userList = q2.getResultList();

        boardList.stream().forEach(b -> {
            User user = userList.stream().filter(u -> u.getId() == b.getUser().getId()).findFirst().get();
            b.setUser(user);
        });
        return boardList;
    }

    public List<Board> findAll(){
        Query query = em.createQuery("select b from Board b order by b.id desc", Board.class);
        return query.getResultList();
    }

    public Board findByIdJoinUser(int id){
        Query query = em.createQuery("select b from Board b join fetch b.user u where b.id = :id", Board.class);
        query.setParameter("id", id);
        return (Board) query.getSingleResult();
    }

    public Board findById(int id){
        // id, title, content, user_id(이질감), created_at
        Board board = em.find(Board.class, id);
        return board;
    }
}