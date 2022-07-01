package com.moja.note.controller.mvc;

import com.moja.note.entity.User;
import com.moja.note.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    //1. Dispatcher -> preuzme request
    //2. HandlerMapper -> kaže dispatcher servlet -> UserController traži
    //3. @Controller -> UserController http://localhost:8080
    //4. Pozvati bazu i učitati podatke iz baze List<User>
    //5. setujemo u model objekat te podatke kako bi ih index.html
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String viewUsers(Model model){
        List<User> userList = userService.getAllUsers();
        model.addAttribute("listOfUsers", userList);
        return "index";
    }

    @GetMapping("/showNewUserForm")
    public String showNewUserForm(Model model){
        User user = new User();
        model.addAttribute("emptyUser", user);
        return "new_user";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("emptyUser") User user){
        userService.saveUser(user);
        return "redirect:/";
    }
}
