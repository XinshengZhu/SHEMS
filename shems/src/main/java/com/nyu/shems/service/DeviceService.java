package com.nyu.shems.service;

import com.nyu.shems.entity.Device;
import com.nyu.shems.entity.Devicetype;

import java.util.HashMap;
import java.util.List;

public interface DeviceService {
    List<Device> getAll();

    HashMap<Integer, String> getInfoBySlid(Integer slid);
}
