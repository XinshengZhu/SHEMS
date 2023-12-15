package com.nyu.shems.service.impl;

import com.nyu.shems.mapper.PriceMapper;
import com.nyu.shems.service.PriceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PriceServiceImpl implements PriceService {

    @Resource
    private PriceMapper priceMapper;

    @Override
    public HashMap<Integer, Float> getPrice(Integer slid) {
        List<Map<Object, Object>> listmap = priceMapper.getPrice(slid);
        HashMap<Integer, Float> result = new HashMap<>();
        for (Map<Object, Object> map : listmap) {
            result.put(Integer.parseInt(map.get("hour").toString()), Float.parseFloat(map.get("price_per_kwh").toString()));
        }
        return result;
    }
}
