package com.example.demo.ec.repository.jdbc;

import com.example.demo.ec.repository.ProductDao;
import com.example.demo.ec.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("ProductDaoJdbc")
public class ProductDaoJdbc implements ProductDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Map<String,Object>> selectList() throws DataAccessException{
        return jdbc.queryForList("SELECT * FROM products");
    };

    @Override
    public Map<String, Object> selectMap(int id) throws DataAccessException {
        return jdbc.queryForMap("SELECT * FROM products WHERE id = ?", id);
    }

}
