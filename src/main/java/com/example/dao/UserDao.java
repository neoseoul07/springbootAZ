package com.example.dao;

import com.example.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.Collection;

/**
 * Created by yaswanth on 20/03/19.
 */
@Repository
public class UserDao {
    @PersistenceContext
    private EntityManager em;

    public Collection getAll() {
        return this.em.createQuery("select u from user u", User.class).getResultList();
    }
}
