package com.example.demo.ec.controller;

import com.example.demo.ec.model.Cart;
import com.example.demo.ec.model.Order;
import com.example.demo.ec.model.User;
import com.example.demo.ec.service.CartService;
import com.example.demo.ec.service.OrderService;
import com.example.demo.ec.service.ProductService;
import com.example.demo.ec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order-history")
public class OrderHistoryController {

    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    OrderService orderService;
    @Autowired
    HttpSession session;


    @GetMapping
    public String OrderHistory(Model model){
        if(session.getAttribute("u_id")==null) {
            model.addAttribute("user", new User());
            return "signin";
        }
        List<Map<String,Object>> list = orderService.selectList((String)session.getAttribute("u_id"));
        ArrayList<Order> orders = new ArrayList<>();
        Order order;
        for(Map<String,Object> omap: list){
            order = new Order();
            order.setId((int)omap.get("id"));
            order.setDate((String)omap.get("str_date"));
            orders.add(order);
        }
        model.addAttribute("orders", orders);
        return "order-history";
    }
    @PostMapping
    public String OrderHistory(Model model, Order order){
        if(session.getAttribute("u_id")==null) {
            User user = new User();
            model.addAttribute("user", user);
            return "signin";
        }
        orderService.insertOrder((String)session.getAttribute("u_id"), order);
        List<Map<String,Object>> list = orderService.selectList((String)session.getAttribute("u_id"));
        ArrayList<Order> orders = new ArrayList<>();
        for(Map<String,Object> omap: list){
            order = new Order();
            order.setId((int)omap.get("id"));
            order.setDate((String)omap.get("str_date"));
            orders.add(order);
        }
        model.addAttribute("orders", orders);
        return "order-history";
    }

}