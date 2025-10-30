package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Question;
import jwp.support.KeyHolder;

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

    public void insert(Question question, KeyHolder keyHolder) throws SQLException {
        String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) VALUES (?, ?, ?, ?, ?)";
        PreparedStatementSetter preparedStatementSetter = preparedStatement -> {
            preparedStatement.setString(1, question.getWriter());
            preparedStatement.setString(2, question.getTitle());
            preparedStatement.setString(3, question.getContents());
            preparedStatement.setString(4, question.getCreatedDate());
            preparedStatement.setInt(5, question.getCountOfAnswer());
        };
        jdbcTemplate.update(sql, preparedStatementSetter, keyHolder);
    }

    public Question findQuestionById(Long questionId) throws SQLException {
        String sql = "SELECT * FROM QUESTIONS WHERE questionId = ?";
        PreparedStatementSetter preparedStatementSetter = preparedStatement -> {
            preparedStatement.setLong(1, questionId);
        };
        RowMapper<Question> rowMapper = resultSet -> new Question(
                resultSet.getLong("questionId"),
                resultSet.getString("writer"),
                resultSet.getString("title"),
                resultSet.getString("contents"),
                resultSet.getString("createdDate"),
                resultSet.getInt("countOfAnswer")
        );
        return jdbcTemplate.queryForObject(sql, preparedStatementSetter, rowMapper);
    }
}
