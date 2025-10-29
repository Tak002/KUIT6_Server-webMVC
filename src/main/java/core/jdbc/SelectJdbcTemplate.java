package core.jdbc;

import jwp.model.User;

import java.sql.SQLException;
import java.util.List;

public class SelectJdbcTemplate<T> {
    private final JdbcTemplate<T> jdbcTemplate = new JdbcTemplate<>();


    public List<T> findAll(String tableName, List<String> columns, Class<T> returnClassType) throws SQLException {
        String sql = "SELECT * FROM "+ tableName;
        return jdbcTemplate.query(sql, generateRowMapper(columns,returnClassType));
    }
    // todo colums, conditions 의 갯수에 따른 sql문 동적 생성
    public T find(String tableName, List<String> conditionCol, List<String> conditionVal, List<String> columns, Class<T> returnClassType) throws SQLException {
        String sql = "SELECT * FROM "+ tableName + " WHERE "+ "? = ?,"* conditionCol.size();
        // column 명은 ?로 주입이 안된다고 함....
        PreparedStatementSetter preparedStatementSetter = preparedStatement -> {
            for(int i = 0; i < conditionCol.size(); i+=2) {
                preparedStatement.setString(i + 1, conditionCol.get(i));
                preparedStatement.setString(i + 2, conditionVal.get(i));
            }
        };
        return jdbcTemplate.queryForObject(sql,preparedStatementSetter, generateRowMapper(columns,returnClassType));
    }


    //by gpt
    public RowMapper<T> generateRowMapper(List<String> columns, Class<T> classType) {
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
