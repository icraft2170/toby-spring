package me.hero.user.dao;

import me.hero.user.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class UserDao {
    private final DataSource dataSource;
    private final JdbcContext jdbcContext;
    private final JdbcTemplate jdbcTemplate;
    private RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));
        return user;
    };


    public UserDao(DataSource dataSource, JdbcContext jdbcContext) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.dataSource = dataSource;
        this.jdbcContext = jdbcContext;
    }

    public void add(User user) throws SQLException {
        this.jdbcTemplate
                .update("insert into users(id, name, password) values(?,?,?)", user.getId(), user.getName(), user.getPassword());
    }


    public User get(String id) throws SQLException {
        return this.jdbcTemplate.queryForObject("select * from users where id = ?",
                new Object[]{id},
                userRowMapper);
    }

    public List<User> getAll() {
        return this.jdbcTemplate.query("select * from users order by id", userRowMapper);
    }

    public void deleteAll() throws SQLException {
        this.jdbcTemplate.update("delete from users");
    }

    public int getCount() throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = dataSource.getConnection();

            ps = c.prepareStatement("select count(*) from users");

            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) {
                try { rs.close(); } catch (SQLException e){}
            }
            if (ps != null) {
                try { ps.close(); } catch (SQLException e){}
            }
            if (c != null) {
                try { c.close(); } catch (SQLException e){}
            }
        }
    }

}
