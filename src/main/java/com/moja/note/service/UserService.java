package com.moja.note.service;

import com.moja.note.entity.User;
import com.moja.note.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Controller -> uc1
 * @Repository ->  ur1
 * @Service -> us1
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        List<User> users = userRepository.findAll();
        return users;
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }
}
