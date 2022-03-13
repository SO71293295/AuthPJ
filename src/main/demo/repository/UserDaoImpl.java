package com.example.demo.repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.MyUser;

@Repository
public class UserDaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Execute a SELECT statement using userName as the search condition to search for users registered in the DB
     * @param userName
     * @return User
     */
    @Override
    public MyUser findUserByUserName(String userName) {
        String sql = "SELECT username, password, name, rolename FROM users WHERE username = ?";

        //Get one user
        Map<String, Object> result = jdbcTemplate.queryForMap(sql, userName);

        //Convert to Entity class (type User)
        MyUser user = convMapToUser(result);

        return user;
    }

    /**
     * Convert the result of executing an SQL SELECT statement (Map<String, Object>) into a User type
     * @param Map<String, Object>
     * @return User
     */
    private MyUser convMapToUser(Map<String, Object> map) {
        MyUser user = new MyUser();

        user.setUserName((String) map.get("username"));
        user.setPassword((String) map.get("password"));
        user.setName((String) map.get("name"));
        user.setRoleName((String) map.get("rolename"));

        return user;
    }
}
