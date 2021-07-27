package com.example.demo.ec.controller;

import com.example.demo.ec.model.Product;
import com.example.demo.ec.model.User;
import com.example.demo.ec.service.ProductService;
import com.example.demo.ec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    HttpSession session;

    @GetMapping
    public String SignUp(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping
    public String SignUp(Model model, @Validated User user, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorList = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("validationError", errorList);
            model.addAttribute("user", user);
            return "signup";
        }
        if(userService.selectMap(user.getId())!=null){
            List<String> errorList = new ArrayList<String>();
            errorList.add("IDが重複しています");
            model.addAttribute("validationError", errorList);
            model.addAttribute("user", user);
            return "signup";
        }
        userService.insertUser(user);
        model.addAttribute("user", new User());
        return "signin";
    }

}