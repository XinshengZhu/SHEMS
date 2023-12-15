package com.nyu.shems.service.impl;

import com.nyu.shems.mapper.EventMapper;
import com.nyu.shems.service.EventService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EventServiceImpl implements EventService {

    @Resource
    private EventMapper eventMapper;

    @Override
    public HashMap<Date, Float> dailyEnergyConsumption(int slid, String start, String end) {
        List<Map<Object, Object>> listmap = eventMapper.dailyEnergyConsumption(slid, start, end);
        HashMap<Date, Float> result = new HashMap<>();
        for (Map<Object, Object> map : listmap) {
            String dateString = map.get("day").toString();
            String[] dateArray = dateString.split("-");
            Date date = new Date(Integer.parseInt(dateArray[0]) - 1900, Integer.parseInt(dateArray[1]) - 1, Integer.parseInt(dateArray[2]));
            result.put(date, Float.parseFloat(map.get("daily_energy_consumption").toString()));
        }
        return result;
    }

    @Override
    public HashMap<Date, Float> monthlyEnergyConsumption(Integer slid, String start, String end) {
        List<Map<Object, Object>> listmap = eventMapper.monthlyEnergyConsumption(slid, start, end);
        HashMap<Date, Float> result = new HashMap<>();
        for (Map<Object, Object> map : listmap) {
            String dateString = map.get("month").toString();
            String[] dateArray = dateString.split("-");
            Date date = new Date(Integer.parseInt(dateArray[0]) - 1900, Integer.parseInt(dateArray[1]) - 1, 1);
            result.put(date, Float.parseFloat(map.get("monthly_energy_consumption").toString()));
        }
        return result;
    }

    @Override
    public HashMap<Integer, Float> enrollmentEnergyConsumption(Integer slid, String start, String end) {
        List<Map<Object, Object>> listmap = eventMapper.enrollmentEnergyConsumption(slid, start, end);
        HashMap<Integer, Float> result = new HashMap<>();
        for (Map<Object, Object> map : listmap) {
            result.put(Integer.parseInt(map.get("enid").toString()), Float.parseFloat(map.get("enrollment_energy_consumption").toString()));
        }
        return result;
    }

    @Override
    public List<Map<Object, Object>> percentageOfSimilarServiceAverage(Integer cid, String start, String end) {
        return eventMapper.percentageOfSimilarServiceAverage(cid, start, end);
    }

    @Override
    public List<Map<Object, Object>> hourlyEnergyCost(Integer enid, String date) {
        return eventMapper.hourlyEnergyCost(enid, date);
    }

    @Override
    public HashMap<Date, Float> dailyEnergyCost(Integer slid, String start, String end) {
        List<Map<Object, Object>> listmap = eventMapper.dailyEnergyCost(slid, start, end);
        HashMap<Date, Float> result = new HashMap<>();
        for (Map<Object, Object> map : listmap) {
            String dateString = map.get("day").toString();
            String[] dateArray = dateString.split("-");
            Date date = new Date(Integer.parseInt(dateArray[0]) - 1900, Integer.parseInt(dateArray[1]) - 1, Integer.parseInt(dateArray[2]));
            result.put(date, Float.parseFloat(map.get("daily_energy_cost").toString()));
        }
        return result;
    }

    @Override
    public HashMap<Date, Float> monthlyEnergyCost(Integer slid, String start, String end) {
        List<Map<Object, Object>> listmap = eventMapper.monthlyEnergyCost(slid, start, end);
        HashMap<Date, Float> result = new HashMap<>();
        for (Map<Object, Object> map : listmap) {
            String dateString = map.get("month").toString();
            String[] dateArray = dateString.split("-");
            Date date = new Date(Integer.parseInt(dateArray[0]) - 1900, Integer.parseInt(dateArray[1]) - 1, 1);
            result.put(date, Float.parseFloat(map.get("monthly_energy_cost").toString()));
        }
        return result;
    }
}
