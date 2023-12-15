package com.nyu.shems.controller;


import com.nyu.shems.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("event")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/daily_energy_consumption")
    public HashMap<Date, Float> dailyEnergyConsumption(Integer slid, String start, String end) {
        return eventService.dailyEnergyConsumption(slid, start, end);
    }

    @GetMapping("/monthly_energy_consumption")
    public HashMap<Date, Float> monthlyEnergyConsumption(Integer slid, String start, String end) {
        return eventService.monthlyEnergyConsumption(slid, start, end);
    }

    @GetMapping("/enrollment_energy_consumption")
    public HashMap<Integer, Float> enrollmentEnergyConsumption(Integer slid, String start, String end) {
        return eventService.enrollmentEnergyConsumption(slid, start, end);
    }

    @GetMapping("/percentage_of_similar_service_average")
    public List<Map<Object, Object>> percentageOfSimilarServiceAverage(Integer cid, String start, String end) {
        return eventService.percentageOfSimilarServiceAverage(cid, start, end);
    }

    @GetMapping("/hourly_energy_cost")
    public List<Map<Object, Object>> hourlyEnergyCost(Integer enid, String date) {
        return eventService.hourlyEnergyCost(enid, date);
    }

    @GetMapping("/daily_energy_cost")
    public HashMap<Date, Float> dailyEnergyCost(Integer slid, String start, String end) {
        return eventService.dailyEnergyCost(slid, start, end);
    }

    @GetMapping("/monthly_energy_cost")
    public HashMap<Date, Float> monthlyEnergyCost(Integer slid, String start, String end) {
        return eventService.monthlyEnergyCost(slid, start, end);
    }
}
