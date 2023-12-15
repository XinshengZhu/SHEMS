package com.nyu.shems.controller;

import com.nyu.shems.entity.Device;
import com.nyu.shems.entity.Devicetype;
import com.nyu.shems.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("device")
public class DeciveController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping("getAll")
    @ResponseBody
    public List<Device> getAll(){
       return deviceService.getAll();
    }

    @GetMapping("getInfoBySlid/{slid}")
    public HashMap<Integer, String> getInfoBySlid(@PathVariable Integer slid){
        return deviceService.getInfoBySlid(slid);
    }
}
