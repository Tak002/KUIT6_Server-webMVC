package core.jdbc;

@FunctionalInterface
public interface PreparedStatementSetter {
    void setParameters(java.sql.PreparedStatement ps) throws java.sql.SQLException;
}
