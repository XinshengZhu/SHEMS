package com.nyu.shems.controller;

import com.nyu.shems.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("price")
public class PriceController {

    @Autowired
    private PriceService priceService;

    @GetMapping("/getPrice/{slid}")
    public HashMap<Integer, Float> getPrice(@PathVariable Integer slid) {
        return priceService.getPrice(slid);
    }

}
