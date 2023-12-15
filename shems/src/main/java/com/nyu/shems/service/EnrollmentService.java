package com.nyu.shems.service;

import com.github.pagehelper.PageInfo;
import com.nyu.shems.entity.Enrollment;

import java.util.List;

public interface EnrollmentService {
    List<Enrollment> getAll();

    int insertSelective(Enrollment enrollment);

    int deleteByPrimaryKey(Integer enid);

    PageInfo<Enrollment> getPagedByCid(int pageNum, int pageSize, int cid);

    List<Enrollment> getBySlid(int slid);
}
