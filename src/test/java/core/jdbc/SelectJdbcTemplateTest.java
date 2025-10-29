package core.jdbc;

import jwp.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import org.mockito.Mockito;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


class SelectJdbcTemplateTest {
    @Test
    public void generateRowMapperTest() throws SQLException {
        SelectJdbcTemplate<User> selectJdbcTemplate = new SelectJdbcTemplate<>();
        RowMapper<User> rowMapper = selectJdbcTemplate.generateRowMapper(
                java.util.List.of("userId","password","name","email"), User.class
        );

        ResultSet rs = mock(ResultSet.class);

        // next() 한 번은 true, 그 다음은 false 라고 응답하도록
        when(rs.next()).thenReturn(true, false);

        // getString("컬럼명")에 대해 우리가 원하는 값 주입
        when(rs.getString("userId")).thenReturn("testUser");
        when(rs.getString("password")).thenReturn("testPass");
        when(rs.getString("name")).thenReturn("Test User");
        when(rs.getString("email")).thenReturn("test@example.com");
        User user = rowMapper.mapRow(rs);
        System.out.println("user = " + user);
        assertNotNull(rowMapper);
    }

}