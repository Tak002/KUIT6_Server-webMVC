package core.jdbc;

import jwp.model.User;

import java.sql.SQLException;
import java.util.List;

public class SelectJdbcTemplate<T> {
    private final JdbcTemplate<T> jdbcTemplate = new JdbcTemplate<>();


    public List<T> findAll(String userTableName, List<String> columns, Class<T> classType) throws SQLException {
        String sql = "SELECT * FROM "+ userTableName;
        return jdbcTemplate.query(sql, generateRowMapper(columns,classType));
    }


    //by gpt
    private RowMapper<T> generateRowMapper(List<String> columns, Class<T> classType) {
        return resultSet -> {
            try {
                Object[] args = new Object[columns.size()];
                Class<?>[] paramTypes = new Class<?>[columns.size()];

                for (int i = 0; i < columns.size(); i++) {
                    args[i] = resultSet.getString(columns.get(i));
                    paramTypes[i] = String.class;
                }

                return classType.getConstructor(paramTypes).newInstance(args);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}
