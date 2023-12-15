package com.nyu.shems.mapper;

import com.nyu.shems.entity.Servicelocation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ServicelocationMapper {
    int deleteByPrimaryKey(Integer slid);

    int insert(Servicelocation record);

    int insertSelective(Servicelocation record);

    Servicelocation selectByPrimaryKey(Integer slid);

    int updateByPrimaryKeySelective(Servicelocation record);

    int updateByPrimaryKey(Servicelocation record);

    List<Servicelocation> getAll();

    List<Servicelocation> getByCid(int cid);
}