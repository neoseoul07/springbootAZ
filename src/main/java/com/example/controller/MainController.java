package com.example.controller;
import com.example.dao.UserDao;
import com.example.model.Address;
import com.example.model.User;
import com.example.dao.UserRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
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
        Session session = em.unwrap(Session.class);
        for (int i =0; i < 10; i++) {
            User n = new User();
            n.setId(i+1);
            n.setName(name);
            n.setEmail(email);
            Address address = new Address();
            address.setAddress("testing");
            n.getAddresses().add(address);
//        entity manager requires @Transactional whereas jpa repository doesn't need it
//        userRepository.save(n);
            em.persist(n);
            if (i!=0 && i%9 == 0)
            {
                session.flush();
                session.clear();
            }
        }
        return "Saved";
    }

    @GetMapping(path="/all")
    public Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
//        Hibernate maintains write-behind cache per session to reduce hitting db multiple times.
//        User u = userRepository.findById(1).get();
//        u = userRepository.findById(6).get();
//        This won't trigger mysql query again as 6 id is fetched already
//        u = userRepository.findById(6).get();
//        return u;

//        flushes - running changes(firing queries) on db though not committed until transaction commits
//        flushing occurs only when it is required
//        (ex. object is changed first and a select query which can hit that row/object is fired
//         then current changes gets flushed and then this query will run).
//        Commits changes only to db in the end of transaction.
//        User u = em.find(User.class, 1);
//        u.setName("test2");
//        userRepository.findById(2).get(); This doesn't flush
//        userRepository.findAll(); This flushes and then select query runs
    }

    @PutMapping(path="/{user_id}")
    @Transactional
    public User updateUser(@PathVariable(value = "user_id") int user_id) throws InterruptedException {
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
        return u;
    }
}