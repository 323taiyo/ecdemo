package com.example.demo.ec.service;

import com.example.demo.ec.model.Cart;
import com.example.demo.ec.repository.CartDao;
import com.example.demo.ec.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
@Service
public class CartService {

    @Autowired
    @Qualifier("CartDaoJdbc")
    CartDao dao;

    public List<Map<String,Object>> selectList(String id){
        return dao.selectList(id);
    }
    public int insertCart(String id, Cart cart) {
        //挿入成功したかの処理を入れる
        return dao.insertCart(id, cart);
    }
    public int deleteCart(String u_id, int p_id) {
        //挿入成功したかの処理を入れる
        return dao.deleteCart(u_id, p_id);
    }
}
