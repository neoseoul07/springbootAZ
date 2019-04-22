package com.example.controller;
import com.example.dao.UserDao;
import com.example.model.Address;
import com.example.model.User;
import com.example.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yaswanth on 15/03/19.
 */
@RestController
@RequestMapping(path="/demo")
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addNewUser (@RequestParam String name, @RequestParam String email) {
        User n = new User();
        n.setName(name);
        n.setEmail(email);
        Address address = new Address();
        address.setAddress("testing");
        n.getAddresses().add(address);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
//        return userDao.getAll();
        return userRepository.findAll();
    }
}