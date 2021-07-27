package com.example.demo.ec.repository;

import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

public interface ProductDao {
    public List<Map<String,Object>> selectList() throws DataAccessException;
    public Map<String, Object> selectMap(int id) throws DataAccessException;
}
