package jwp.dao;

import jwp.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserDao {
    private final EntityManager em;

    public void insert(User user) throws SQLException {
        em.persist(user);
    }

    public void update(User user) throws SQLException {
        em.merge(user);
    }


    public List<User> findAll() throws  SQLException {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    public User findUserById(String userId) throws  SQLException {
        return em.find(User.class, userId);
    }
}
