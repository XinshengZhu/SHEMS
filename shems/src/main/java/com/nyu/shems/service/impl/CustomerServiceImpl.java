package com.nyu.shems.service.impl;

import com.nyu.shems.entity.Customer;
import com.nyu.shems.mapper.CustomerMapper;
import com.nyu.shems.service.CustomerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Resource
    private CustomerMapper customerMapper;

    @Override
    public Customer selectByPrimaryKey(Integer id) {
        return customerMapper.selectByPrimaryKey(id);
    }

    @Override
    public Customer login(String username, String password) {
        return customerMapper.login(username, password);
    }

    @Override
    public int insertSelective(Customer customer) {
        return customerMapper.insertSelective(customer);
    }

    @Override
    public Customer checkUsername(String username) {
        return customerMapper.checkUsername(username);
    }
}




