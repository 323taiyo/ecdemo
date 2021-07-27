package com.example.demo.ec.repository;

import com.example.demo.ec.model.User;
import org.springframework.dao.DataAccessException;

import java.util.Map;

public interface UserDao {
    public Map<String, Object> selectMap(String id) throws DataAccessException;
    public String selectAddress(String id) throws DataAccessException;
    public int insertUser(User user) throws DataAccessException;
}
