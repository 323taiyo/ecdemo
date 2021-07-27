package com.example.demo.ec.repository;

import com.example.demo.ec.model.Order;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

public interface OrderDao {
    public List<Map<String,Object>> selectList(String id) throws DataAccessException;
    public Map<String, Object> selectMap(int id) throws DataAccessException;
    public int insertOrder(String id, Order order) throws DataAccessException;
}
