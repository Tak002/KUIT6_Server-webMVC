package core.jdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate<T> {
    public void update(String sql, PreparedStatementSetter preparedStatementSetter) throws SQLException {
        try(Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatementSetter.setParameters(preparedStatement);
            preparedStatement.executeUpdate();
        }
    }

    public List<T> query(String sql, RowMapper<T> rowMapper) throws  SQLException {
        List<T> objects = new ArrayList<>();

        try(Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();){

            while(resultSet.next()){
                T object = rowMapper.mapRow(resultSet);
                objects.add(object);
            }
        }
        return objects;
    }

    public T queryForObject(String sql, PreparedStatementSetter preparedStatementSetter, RowMapper<T> rowMapper) throws  SQLException {
        T object = null;

        try(Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            ResultSet resultSet = preparedStatement.executeQuery();
            preparedStatementSetter.setParameters(preparedStatement);
            if(resultSet.next()){
                object = rowMapper.mapRow(resultSet);
            }
        }
        return object;
    }
}
