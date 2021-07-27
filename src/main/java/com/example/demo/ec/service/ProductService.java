package com.example.demo.ec.service;

import com.example.demo.ec.repository.ProductDao;
import com.example.demo.ec.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
@Service
public class ProductService {

    @Autowired
    @Qualifier("ProductDaoJdbc")
    ProductDao dao;

    public List<Map<String,Object>> selectList(){
        return dao.selectList();
    }
    public Map<String, Object> selectMap(int id){ return dao.selectMap(id); }

}
