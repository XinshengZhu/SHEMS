package com.nyu.shems;

import com.nyu.shems.entity.Servicelocation;
import com.nyu.shems.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ShemsApplicationTests {

    @Resource
    private EventService eventService;

    @Resource
    private ServicelocationService servicelocationService;

    @Resource
    private CustomerService customerService;

    @Resource
    private DeviceService deviceService;

    @Resource
    private PriceService priceService;

    @Test
    void contextLoads() {
//        System.out.println(servicelocationService.getAll());
//        System.out.println(customerService.selectByPrimaryKey(1));
        HashMap<Date, Float> test =  eventService.dailyEnergyCost(1, "2022-08-01", "2022-08-10");
        HashMap<Date, Float> test2 =  eventService.monthlyEnergyCost(1, "2022-08", "2022-09");
//        HashMap<Integer, Float> test3 =  eventService.enrollmentEnergyConsumption(1, "2022-08-01", "2022-08-10");
//        HashMap<Integer, String> test4 =  deviceService.getInfoBySlid(1);
//        List<Map<Object, Object>> test = eventService.hourlyEnergyCost(1, "2022-08-13");
//        for (Map<Object, Object> map : test) {
//            System.out.println(map);
//            map.keySet().forEach(System.out::println);
//        }


    }

}
