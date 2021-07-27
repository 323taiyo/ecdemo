package com.example.demo.ec.repository;

import com.example.demo.ec.model.Cart;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

public interface CartDao {
    public List<Map<String,Object>> selectList(String id) throws DataAccessException;
    public int insertCart(String id, Cart cart) throws DataAccessException;
    public int deleteCart(String u_id, int p_id) throws DataAccessException;
}
