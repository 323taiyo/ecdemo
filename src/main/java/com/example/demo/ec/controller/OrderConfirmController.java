package com.example.demo.ec.controller;

import com.example.demo.ec.model.Cart;
import com.example.demo.ec.model.Order;
import com.example.demo.ec.model.Product;
import com.example.demo.ec.model.User;
import com.example.demo.ec.service.CartService;
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
@RequestMapping("/order-confirm")
public class OrderConfirmController {

    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    CartService cartService;
    @Autowired
    HttpSession session;


    @PostMapping
    public String OrderConfirm(Model model){
        if(session.getAttribute("u_id")==null) {
            model.addAttribute("user", new User());
            return "signin";
        }
        List<Map<String,Object>> list = cartService.selectList((String)session.getAttribute("u_id"));
        ArrayList<Cart> carts = new ArrayList<>();
        Cart cart = null;
        for(Map<String,Object> cmap: list){
            Map<String,Object> pmap = productService.selectMap((int)cmap.get("p_id"));
            cart = new Cart();
            cart.setId((int)cmap.get("p_id"));
            cart.setPrice((int)cmap.get("price"));
            cart.setQuantity((int)cmap.get("quantity"));
            cart.setName((String)pmap.get("name"));
            cart.setImage((String)pmap.get("image"));
            carts.add(cart);
        }
        model.addAttribute("carts", carts);
        Order order = new Order();
        order.setAddress(userService.selectAddress((String)session.getAttribute("u_id")));
        model.addAttribute("order", order);
        return "order-confirm";
    }

}