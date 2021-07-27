package com.example.demo.ec.service;

import com.example.demo.ec.model.Order;
import com.example.demo.ec.repository.OrderDao;
import com.example.demo.ec.repository.OrderDetailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
@Service
public class OrderDetailService {

    @Autowired
    @Qualifier("OrderDetailDaoJdbc")
    OrderDetailDao dao;

    public List<Map<String,Object>> selectList(String id){
        return dao.selectList(id);
    }

}
