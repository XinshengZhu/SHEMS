package com.nyu.shems.service;

import com.nyu.shems.entity.Customer;

public interface CustomerService {
    Customer selectByPrimaryKey(Integer cid);

    Customer login(String username, String password);

    int insertSelective(Customer customer);

    Customer checkUsername(String username);
}