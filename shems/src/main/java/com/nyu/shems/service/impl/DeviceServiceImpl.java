package com.nyu.shems.service.impl;

import com.nyu.shems.entity.Device;
import com.nyu.shems.mapper.DeviceMapper;
import com.nyu.shems.service.DeviceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Resource
    private DeviceMapper deviceMapper;

    @Override
    public List<Device> getAll() {
        return deviceMapper.getAll();
    }

    @Override
    public HashMap<Integer, String> getInfoBySlid(Integer slid) {
        List<Map<Object, Object>> listmap = deviceMapper.getInfoBySlid(slid);
        HashMap<Integer, String> result = new HashMap<>();
        for (Map<Object, Object> map : listmap) {
            result.put(Integer.parseInt(map.get("enid").toString()), map.get("device_info").toString());
        }
        return result;
    }
}
