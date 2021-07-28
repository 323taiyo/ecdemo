package com.example.demo.ec.controller;

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

@Controller
@RequestMapping("/signout")
public class SignOutController {

    @Autowired
    HttpSession session;

    @GetMapping
    public String SignOut(Model model) {
        session.removeAttribute("u_id");
        model.addAttribute("user", new User());
        return "signin";
    }
}