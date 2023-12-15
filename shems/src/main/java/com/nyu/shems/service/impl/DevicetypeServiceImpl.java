package com.nyu.shems.service.impl;

import com.nyu.shems.entity.Devicetype;
import com.nyu.shems.mapper.DevicetypeMapper;
import com.nyu.shems.service.DevicetypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DevicetypeServiceImpl implements DevicetypeService {

    @Resource
    private DevicetypeMapper devicetypeMapper;

    @Override
    public List<Devicetype> getAll() {
        return devicetypeMapper.getAll();
    }
}
