package com.nyu.shems.mapper;

import com.nyu.shems.entity.Enrollment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EnrollmentMapper {
    int deleteByPrimaryKey(Integer enid);

    int insert(Enrollment record);

    int insertSelective(Enrollment record);

    Enrollment selectByPrimaryKey(Integer enid);

    int updateByPrimaryKeySelective(Enrollment record);

    int updateByPrimaryKey(Enrollment record);

    List<Enrollment> getAll();

    List<Enrollment> getByCid(int cid);

    List<Enrollment> getBySlid(int slid);
}