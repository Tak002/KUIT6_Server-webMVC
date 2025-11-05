package jwp.dao;


import jwp.model.Question;
import jwp.support.KeyHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuestionDao {
    private final EntityManager em;
    public List<Question> findAll() throws SQLException{
        return em.createQuery("select u from Question u", Question.class).getResultList();
    }

    public void insert(Question question, KeyHolder keyHolder) throws SQLException {
        em.persist(question);
    }

    public Question findQuestionById(Long questionId) throws SQLException {
        return em.find(Question.class, questionId);
    }
}
