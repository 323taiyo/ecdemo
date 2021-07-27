package com.example.demo.ec.controller;

import com.example.demo.ec.model.Cart;
import com.example.demo.ec.model.Product;
import com.example.demo.ec.model.User;
import com.example.demo.ec.service.ProductService;
import com.example.demo.ec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/signin")
public class SignInController {

    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    HttpSession session;

    @PostMapping
    public String SignIn(Model model, User user) {
        Map<String, Object> umap = userService.selectMap(user.getId());
        if(umap==null){
            model.addAttribute("user", user);
            return "signin";
        }
        else if(!((String)umap.get("password")).equals(user.getPass())){
        model.addAttribute("user", user);
        return "signin";
        }

        session.setAttribute("u_id", user.getId());

        List<Map<String,Object>> list = productService.selectList();
        ArrayList<Product> products = new ArrayList<>();
        Product product = null;
        for(Map<String,Object> map: list){
            product = new Product();
            product.setId((int)map.get("id"));
            product.setName((String)map.get("name"));
            product.setPrice((int)map.get("price"));
            product.setStock((int)map.get("stock"));
            product.setImage((String)map.get("image"));
            products.add(product);
        }
        model.addAttribute("products", products);
        return "list";

    }

}