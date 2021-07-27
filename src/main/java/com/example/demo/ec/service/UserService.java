package com.example.demo.ec.service;

import com.example.demo.ec.model.User;
import com.example.demo.ec.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Transactional
@Service
public class UserService {

    @Autowired
    @Qualifier("UserDaoJdbc")
    UserDao dao;

    public Map<String, Object> selectMap(String id){ return dao.selectMap(id); }
    public String selectAddress(String id){ return dao.selectAddress(id); }
    public int insertUser(User user){ return dao.insertUser(user); }

}
