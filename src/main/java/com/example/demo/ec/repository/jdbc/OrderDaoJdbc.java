package com.example.demo.ec.repository.jdbc;

import com.example.demo.ec.model.Order;
import com.example.demo.ec.repository.OrderDao;
import com.example.demo.ec.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("OrderDaoJdbc")
public class OrderDaoJdbc implements OrderDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Map<String,Object>> selectList(String id) throws DataAccessException {
        return jdbc.queryForList("SELECT id, DATE_FORMAT(date, '%Y年%c月%e日%H時%i分') AS str_date FROM orders WHERE u_id = ?", id);
    }

    @Override
    public Map<String, Object> selectMap(int id) throws DataAccessException {
        return jdbc.queryForMap("SELECT * FROM orders WHERE id = ?", id);
    }

    @Override
    public int insertOrder(String id, Order order) throws DataAccessException {
        return jdbc.update("INSERT INTO orders (u_id, address) VALUES(?, ?)",
                id, order.getAddress());
    }
}
