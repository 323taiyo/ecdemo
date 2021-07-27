package com.example.demo.ec.repository;

import com.example.demo.ec.model.Order;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

public interface OrderDetailDao {
    public List<Map<String,Object>> selectList(String id) throws DataAccessException;
}
