package com.example.demo.ec.service;

import com.example.demo.ec.model.Order;
import com.example.demo.ec.repository.OrderDao;
import com.example.demo.ec.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
@Service
public class OrderService {

    @Autowired
    @Qualifier("OrderDaoJdbc")
    OrderDao dao;

    public List<Map<String,Object>> selectList(String id){
        return dao.selectList(id);
    }
    public Map<String, Object> selectMap(int id){ return dao.selectMap(id); }
    public int insertOrder(String id, Order order){return dao.insertOrder(id, order); }

}
