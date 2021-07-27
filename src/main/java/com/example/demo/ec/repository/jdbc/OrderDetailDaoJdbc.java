package com.example.demo.ec.repository.jdbc;

import com.example.demo.ec.model.Order;
import com.example.demo.ec.repository.OrderDao;
import com.example.demo.ec.repository.OrderDetailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("OrderDetailDaoJdbc")
public class OrderDetailDaoJdbc implements OrderDetailDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Map<String,Object>> selectList(String id) throws DataAccessException {
        return jdbc.queryForList("SELECT p_id, products.name AS name, order_details.price AS price, quantity FROM order_details JOIN products on order_details.p_id = products.id WHERE o_id = ?", id);
    }
}
