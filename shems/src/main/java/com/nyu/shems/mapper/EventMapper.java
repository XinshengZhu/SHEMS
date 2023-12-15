package com.nyu.shems.mapper;

import com.nyu.shems.entity.Event;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface EventMapper {
    int deleteByPrimaryKey(Integer evid);

    int insert(Event record);

    int insertSelective(Event record);

    Event selectByPrimaryKey(Integer evid);

    int updateByPrimaryKeySelective(Event record);

    int updateByPrimaryKey(Event record);

    @MapKey("day")
    List<Map<Object, Object>> dailyEnergyConsumption(int slid, String start, String end);

    @MapKey("month")
    List<Map<Object, Object>> monthlyEnergyConsumption(int slid, String start, String end);

    @MapKey("enid")
    List<Map<Object, Object>> enrollmentEnergyConsumption(Integer slid, String start, String end);

    List<Map<Object, Object>> percentageOfSimilarServiceAverage (Integer cid, String start, String end);

    List<Map<Object, Object>> hourlyEnergyCost (Integer enid, String date);

    @MapKey("day")
    List<Map<Object, Object>> dailyEnergyCost(Integer slid, String start, String end);

    @MapKey("month")
    List<Map<Object, Object>> monthlyEnergyCost(Integer slid, String start, String end);
}