package com.example.demo.ec.repository.jdbc;

import com.example.demo.ec.model.Cart;
import com.example.demo.ec.repository.CartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("CartDaoJdbc")
public class CartDaoJdbc implements CartDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Map<String,Object>> selectList(String id) throws DataAccessException{
        return jdbc.queryForList("SELECT * FROM carts WHERE u_id = ?", id);
    };

    @Override
    public int insertCart(String id, Cart cart) throws DataAccessException{
        return jdbc.update("INSERT INTO carts (u_id, p_id, price, quantity) VALUES(?, ?, ?, ?) ON DUPLICATE KEY UPDATE quantity = quantity + ?;",
                id, cart.getId(), cart.getPrice(), cart.getQuantity(), cart.getQuantity());
    }

    @Override
    public int deleteCart(String u_id, int p_id) throws DataAccessException{
        return jdbc.update("DELETE FROM carts WHERE u_id = ? AND p_id = ?", u_id, p_id);
    }
}
