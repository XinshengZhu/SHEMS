package com.nyu.shems.mapper;

import com.nyu.shems.entity.Device;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface DeviceMapper {
    int deleteByPrimaryKey(Integer did);

    int insert(Device record);

    int insertSelective(Device record);

    Device selectByPrimaryKey(Integer did);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);

    List<Device> getAll();

    @MapKey("enid")
    List<Map<Object, Object>> getInfoBySlid(Integer slid);
}