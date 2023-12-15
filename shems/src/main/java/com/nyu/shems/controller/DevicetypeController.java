package com.nyu.shems.controller;


import com.nyu.shems.entity.Devicetype;
import com.nyu.shems.service.DevicetypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("devicetype")
public class DevicetypeController {

    @Autowired
    private DevicetypeService devicetypeService;

    @GetMapping("getAll")
    @ResponseBody
    public List<Devicetype> getAll() {
        return devicetypeService.getAll();
    }
}
