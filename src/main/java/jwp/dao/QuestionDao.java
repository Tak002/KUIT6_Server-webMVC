package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;
import jwp.model.Question;

import java.sql.SQLException;
import java.util.List;

public class QuestionDao {
    private static QuestionDao questionDao;
    private QuestionDao() {
    }

    public static QuestionDao getInstance() {
        if (questionDao == null) {
            questionDao = new QuestionDao();
            return questionDao;
        }
        return questionDao;
    }

    private final JdbcTemplate<Question> jdbcTemplate  = new JdbcTemplate<>();


    public List<Question> findAll() throws SQLException{
        String sql = "SELECT * FROM QUESTIONS";
        RowMapper<Question> rowMapper = resultSet -> new Question(
                resultSet.getLong("questionId"),
                resultSet.getString("title"),
                resultSet.getString("writer"),
                resultSet.getString("contents"),
                resultSet.getString("createdDate"),
                resultSet.getInt("countOfAnswer")
        );
        return jdbcTemplate.query(sql, rowMapper);
    }
}
