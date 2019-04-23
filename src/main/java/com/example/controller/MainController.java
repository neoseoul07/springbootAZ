package com.example.controller;
import com.example.dao.UserDao;
import com.example.model.Address;
import com.example.model.User;
import com.example.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Iterator;

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

    @PersistenceContext
    private EntityManager em;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @Transactional
    public String addNewUser (@RequestParam String name, @RequestParam String email) {
        User n = new User();
        n.setName(name);
        n.setEmail(email);
        Address address = new Address();
        address.setAddress("testing");
        n.getAddresses().add(address);
//        entity manager requires @Transactional whereas jpa repository doesn't need it
//        userRepository.save(n);
        em.persist(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
//        Hibernate maintains cache per session to reduce hitting db multiple times.
//        write-behind cache -> flushes changes to cache only when it
//        is required(ex. object with id param changed and fetching again in same session/transaction).
//        Commits changes only to db in the end of transaction.

//        User u = userRepository.findById(1).get();
//        u = userRepository.findById(6).get();
//        This won't trigger mysql query again as 6 id is fetched already
//        u = userRepository.findById(6).get();
//        return u;

    }

    @PutMapping(path="/{user_id}")
    @Transactional
    public User updateUser(@PathVariable(value = "user_id") int user_id) throws InterruptedException {
//        User u = userRepository.findById(1).get();
//        An exclusive lock blocks both shared lock & exclusive lock
//        An shared lock blocks only exclusive lock
        User u = em.find(User.class, 1, LockModeType.PESSIMISTIC_WRITE);
//        System.out.println(u.getName());
        if (user_id == 1) {
            u.setName("test2");
            u.setEmail("testing@gmail.com");
        } else {
            u.setName("test3");
        }
        Thread.sleep(10000);
//        u = userRepository.findById(user_id).get();
        return u;
    }
}