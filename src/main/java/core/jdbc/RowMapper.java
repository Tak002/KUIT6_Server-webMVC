package core.jdbc;

@FunctionalInterface
public interface RowMapper<T> {
    T mapRow(java.sql.ResultSet rs) throws java.sql.SQLException;
}
