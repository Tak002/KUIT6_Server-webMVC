package core.jdbc;

import java.sql.SQLException;
import java.util.List;

public class UpdateJdbcTemplate<T> {
    private final JdbcTemplate<T> jdbcTemplate = new JdbcTemplate<>();

    public void update(String tableName, List<String> columns, List<String> datas) throws SQLException {
        // todo colums, conditions 의 갯수에 따른 sql문 동적 생성
        String sql = "UPDATE ? SET ? = ?, ? = ?, ? = ? WHERE ? = ?";
        PreparedStatementSetter preparedStatementSetter = preparedStatement -> {
            preparedStatement.setString(1, tableName);
            for(int i = 0; i < datas.size(); i+=2) {
                preparedStatement.setString(i + 2, datas.get(i));
            }
        };
        jdbcTemplate.update(sql, preparedStatementSetter);
    }}
