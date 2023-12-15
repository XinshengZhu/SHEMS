package com.nyu.shems.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface EventService {
    HashMap<Date, Float> dailyEnergyConsumption(int slid, String start, String end);

    HashMap<Date, Float> monthlyEnergyConsumption(Integer slid, String start, String end);

    HashMap<Integer, Float> enrollmentEnergyConsumption(Integer slid, String start, String end);

    List<Map<Object, Object>> percentageOfSimilarServiceAverage (Integer cid, String start, String end);

    List<Map<Object, Object>> hourlyEnergyCost (Integer enid, String date);


    HashMap<Date, Float> dailyEnergyCost(Integer slid, String start, String end);

   HashMap<Date, Float> monthlyEnergyCost(Integer slid, String start, String end);
}
