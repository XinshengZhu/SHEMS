package com.nyu.shems.service;

import java.util.HashMap;

public interface PriceService {
    HashMap<Integer, Float> getPrice(Integer slid);
}
