package core.jdbc;

import java.sql.SQLException;
import java.util.List;

public class UpdateJdbcTemplate<T> {
    private final JdbcTemplate<T> jdbcTemplate = new JdbcTemplate<>();

    public void update(String tableName, List<String> columns) throws SQLException {
        String sql = "UPDATE ? SET password = ?, name = ?, email = ? WHERE userId = ?";
        PreparedStatementSetter preparedStatementSetter = preparedStatement -> {
            preparedStatement.setString(1, tableName);
            for(int i = 0; i < columns.size(); i++) {
                preparedStatement.setString(i + 2, columns.get(i));
            }
        };
        jdbcTemplate.update(sql, preparedStatementSetter);
    }}
