package com.example.dao;

import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by yaswanth on 15/03/19.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}