package com.nyu.shems.mapper;

import com.nyu.shems.entity.Devicetype;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DevicetypeMapper {
    int deleteByPrimaryKey(Integer dtid);

    int insert(Devicetype record);

    int insertSelective(Devicetype record);

    Devicetype selectByPrimaryKey(Integer dtid);

    int updateByPrimaryKeySelective(Devicetype record);

    int updateByPrimaryKey(Devicetype record);

    List<Devicetype> getAll();
}