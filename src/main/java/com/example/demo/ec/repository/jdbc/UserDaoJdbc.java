package com.example.demo.ec.repository.jdbc;

import com.example.demo.ec.model.Order;
import com.example.demo.ec.model.User;
import com.example.demo.ec.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository("UserDaoJdbc")
public class UserDaoJdbc implements UserDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Map<String, Object> selectMap(String id) throws DataAccessException{
        try {
            return jdbc.queryForMap("SELECT * FROM users WHERE id = ?", id);
        }catch(DataAccessException e){
            return null;
        }
    };

    @Override
    public String selectAddress(String id) throws DataAccessException{
        Map<String, Object> map = jdbc.queryForMap("SELECT address FROM users WHERE id = ?", id);
        return (String)map.get("address");
    };

    @Override
    public int insertUser(User user) throws DataAccessException {
        return jdbc.update("INSERT INTO users (id, password, name, address, tel) VALUES(?, ?, ?, ?, ?)",
                user.getId(), user.getPass(), user.getName(), user.getAddress(), user.getTel());
    }
}
