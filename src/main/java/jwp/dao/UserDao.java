package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserDao {
    private static UserDao userDao;
    private UserDao() {
    }

    public static UserDao getInstance() {
        if (userDao == null) {
            userDao = new UserDao();
            return userDao;
        }
        return userDao;
    }
    private final JdbcTemplate<User> jdbcTemplate  = new JdbcTemplate<>();

    public void insert(User user) throws SQLException {
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        PreparedStatementSetter preparedStatementSetter = preparedStatement -> {
            preparedStatement.setString(1, user.getUserId());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getEmail());
        };
        jdbcTemplate.update(sql, preparedStatementSetter);
    }

    public void update(User user) throws SQLException {
        String sql = "UPDATE USERS SET password = ?, name = ?, email = ? WHERE userId = ?";
        PreparedStatementSetter preparedStatementSetter = preparedStatement -> {
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getUserId());
        };
        jdbcTemplate.update(sql, preparedStatementSetter);
    }

    public void delete(User user) throws SQLException {
        String sql = "DELETE FROM USERS WHERE userId = ?";
        PreparedStatementSetter preparedStatementSetter = preparedStatement -> {
            preparedStatement.setString(1, user.getUserId());
        };
        jdbcTemplate.update(sql, preparedStatementSetter);
    }

    public List<User> findAll() throws  SQLException {
        String sql = "SELECT * FROM USERS";
        RowMapper<User> rowMapper = resultSet -> new User(
                resultSet.getString("userId"),
                resultSet.getString("password"),
                resultSet.getString("name"),
                resultSet.getString("email")
        );
        return jdbcTemplate.query(sql, rowMapper);
    }

    public User findUserById(String userId) throws  SQLException {
        String sql = "SELECT * FROM USERS where userId = ?";
        PreparedStatementSetter preparedStatementSetter = preparedStatement -> {
            preparedStatement.setString(1, userId);
        };
        RowMapper<User> rowMapper = resultSet -> new User(
                resultSet.getString("userId"),
                resultSet.getString("password"),
                resultSet.getString("name"),
                resultSet.getString("email")
        );
        return jdbcTemplate.queryForObject(sql, preparedStatementSetter, rowMapper);
    }

}
