package jwp.dao;

import core.jdbc.*;
import jwp.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserDao {
    private final JdbcTemplate<User> jdbcTemplate  = new JdbcTemplate();
    private final InsertJdbcTemplate<User> insertJdbcTemplate = new InsertJdbcTemplate();
    private final UpdateJdbcTemplate<User> updateJdbcTemplate = new UpdateJdbcTemplate();
    private final String USER_TABLE_NAME = "USERS";
    public void insert(User user) throws SQLException {
        insertJdbcTemplate.run(USER_TABLE_NAME, List.of(
                user.getUserId(),
                user.getPassword(),
                user.getName(),
                user.getEmail()
        ));
    }

    public void update(User user) throws SQLException {
        updateJdbcTemplate.run(USER_TABLE_NAME, List.of(
                user.getPassword(),
                user.getName(),
                user.getEmail(),
                user.getUserId()
        ));
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
        RowMapper rowMapper = resultSet -> new User(
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
        RowMapper rowMapper = resultSet -> new User(
                resultSet.getString("userId"),
                resultSet.getString("password"),
                resultSet.getString("name"),
                resultSet.getString("email")
        );
        return jdbcTemplate.queryForObject(sql, preparedStatementSetter, rowMapper);
    }

}
