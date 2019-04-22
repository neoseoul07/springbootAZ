package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by yaswanth on 06/04/19.
 */
@Entity(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String address;

    private Integer user_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() { return address; }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getUserId() {
        return user_id;
    }

    public void setUserId(Integer userId) {
        this.user_id = userId;
    }
}
