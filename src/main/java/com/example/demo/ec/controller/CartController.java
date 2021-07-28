package com.example.demo.ec.controller;

import com.example.demo.ec.model.Cart;
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
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ProductService productService;
    @Autowired
    CartService cartService;
    @Autowired
    HttpSession session;

    @GetMapping
    public String Cart(Model model) {
        if(session.getAttribute("u_id")==null) {
            model.addAttribute("user", new User());
            return "signin";
        }
        List<Map<String,Object>> list = cartService.selectList((String)session.getAttribute("u_id"));
        ArrayList<Cart> carts = new ArrayList<>();
        Cart cart;
        int sum = 0;
        for(Map<String,Object> cmap: list){
            Map<String,Object> pmap = productService.selectMap((int)cmap.get("p_id"));
            cart = new Cart();
            cart.setId((int)cmap.get("p_id"));
            cart.setPrice((int)cmap.get("price"));
            cart.setQuantity((int)cmap.get("quantity"));
            cart.setName((String)pmap.get("name"));
            cart.setImage((String)pmap.get("image"));
            if((int)pmap.get("stock")>(int)cmap.get("quantity")){
                carts.add(cart);
                sum += cart.getPrice()*cart.getQuantity();
            }else{
                //消した場合のメッセージ表示を入れる
                cartService.deleteCart((String)session.getAttribute("u_id"), (int)cmap.get("p_id"));
            }
        }
        model.addAttribute("carts", carts);
        model.addAttribute("sum", sum);
        return "cart";
    }

    @PostMapping
    public String Cart(Model model, Cart cart){
        if(session.getAttribute("u_id")==null) {
            User user = new User();
            model.addAttribute("user", user);
            return "signin";
        }
        cartService.insertCart((String)session.getAttribute("u_id"), cart);
        List<Map<String,Object>> list = cartService.selectList((String)session.getAttribute("u_id"));
        ArrayList<Cart> carts = new ArrayList<>();
        int sum = 0;
        for(Map<String,Object> cmap: list){
            Map<String,Object> pmap = productService.selectMap((int)cmap.get("p_id"));
            cart = new Cart();
            cart.setId((int)cmap.get("p_id"));
            cart.setPrice((int)cmap.get("price"));
            cart.setQuantity((int)cmap.get("quantity"));
            cart.setName((String)pmap.get("name"));
            cart.setImage((String)pmap.get("image"));
            if((int)pmap.get("stock")>(int)cmap.get("quantity")){
                carts.add(cart);
                sum += cart.getPrice()*cart.getQuantity();
            }else{
                //消した場合のメッセージ表示を入れる
                cartService.deleteCart((String)session.getAttribute("u_id"), (int)cmap.get("p_id"));
            }
        }
        model.addAttribute("carts", carts);
        model.addAttribute("sum", sum);
        return "cart";
    }

}