package com.example.demo.ec.controller;
import com.example.demo.ec.model.Cart;
import com.example.demo.ec.model.Product;
import com.example.demo.ec.model.User;
import com.example.demo.ec.service.ProductService;
import com.example.demo.ec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;

    @GetMapping("/")
    public String Index(Model model, @RequestParam(name = "id", required = false) String id) {
        if(id==null) {
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
        Map<String, Object> map = productService.selectMap(Integer.parseInt(id));

        Product product = new Product();
        product.setId((int)map.get("id"));
        product.setName((String)map.get("name"));
        product.setPrice((int)map.get("price"));
        product.setStock((int)map.get("stock"));
        product.setText((String)map.get("text"));
        product.setImage((String)map.get("image"));
        model.addAttribute("product", product);

        Cart cart = new Cart();
        cart.setId((int)map.get("id"));
        cart.setPrice((int)map.get("price"));
        cart.setQuantity(1);
        model.addAttribute("cart", cart);

        return "detail";
    }

}