package com.nyu.shems.controller;

import com.nyu.shems.entity.Customer;
import com.nyu.shems.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("login")
    public Customer login(String username, String password) {
        return customerService.login(username, password);
    }

    @GetMapping("checkUsername")
    public Customer checkUsername(String username) {
        return customerService.checkUsername(username);
    }

    @PostMapping("register")
    public Customer register(@RequestBody Customer customer) {
        if (customerService.insertSelective(customer) == 1) {
            return customer;
        } else {
            return null;
        }
    }
}


