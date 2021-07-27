package com.example.demo.ec.controller;

import com.example.demo.ec.model.*;
import com.example.demo.ec.service.OrderDetailService;
import com.example.demo.ec.service.OrderService;
import com.example.demo.ec.service.ProductService;
import com.example.demo.ec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class OrderDetailController {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderDetailService orderDetailService;
    @Autowired
    HttpSession session;

    @GetMapping("/order-detail")
    public String OrderDetail(Model model, @RequestParam(name = "id") String id) {
        if(session.getAttribute("u_id")==null) {
            model.addAttribute("user", new User());
            return "signin";
        }

        Map<String, Object> omap = orderService.selectMap(Integer.parseInt(id));
        if(!session.getAttribute("u_id").equals(omap.get("u_id"))) {
            return "forward:/order-history";
        }
        List<Map<String, Object>> list = orderDetailService.selectList(id);
        ArrayList<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail orderDetail;
        for(Map<String, Object> map: list){
            orderDetail = new OrderDetail();
            orderDetail.setId((int)map.get("p_id"));
            orderDetail.setName((String)map.get("name"));
            orderDetail.setPrice((int)map.get("price"));
            orderDetail.setQuantity((int)map.get("quantity"));
            orderDetails.add(orderDetail);
        }
        model.addAttribute("orderDetails", orderDetails);

        return "order-detail";
    }

}