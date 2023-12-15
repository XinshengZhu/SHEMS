package com.nyu.shems.mapper;

import com.nyu.shems.entity.Price;
import com.nyu.shems.entity.PriceKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface PriceMapper {
    int deleteByPrimaryKey(PriceKey key);

    int insert(Price record);

    int insertSelective(Price record);

    Price selectByPrimaryKey(PriceKey key);

    int updateByPrimaryKeySelective(Price record);

    int updateByPrimaryKey(Price record);

    List<Map<Object, Object>> getPrice(Integer slid);
}