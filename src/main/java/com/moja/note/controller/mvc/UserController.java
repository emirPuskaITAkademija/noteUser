package com.moja.note.controller.mvc;

import com.moja.note.entity.User;
import com.moja.note.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * <li>http://localhost:8080 GET -> UserController</li>
 * <li>http://localhost:8080/ GET -> viewUsers()</li>
 * <li>http://localhost:8080/showNewUserForm GET -> showNewUserForm()</li>
 * <li>showNewUserForm/saveUser POST -> saveUser()</li>
 */
@Controller
public class UserController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String viewUsers(Model model){
        List<User> userList = userService.getAllUsers();
        model.addAttribute("listOfUsers", userList);
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }

    @GetMapping("/register")
    public String showNewUserForm(Model model){
        User user = new User();
        model.addAttribute("emptyUser", user);
        return "register";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("emptyUser") User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole("USER");
            userService.saveUser(user);
            return "redirect:/";
        }catch (Exception e){
            return "redirect:/register?error=Bad registration";
        }
    }
}
