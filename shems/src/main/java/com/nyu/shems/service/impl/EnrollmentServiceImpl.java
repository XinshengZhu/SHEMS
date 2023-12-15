package com.nyu.shems.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nyu.shems.entity.Enrollment;
import com.nyu.shems.mapper.EnrollmentMapper;
import com.nyu.shems.service.EnrollmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Resource
    private EnrollmentMapper enrollmentMapper;

    @Override
    public List<Enrollment> getAll() {
        return enrollmentMapper.getAll();
    }

    @Override
    public int insertSelective(Enrollment enrollment) {
        return enrollmentMapper.insertSelective(enrollment);
    }

    @Override
    public int deleteByPrimaryKey(Integer enid) {
        return enrollmentMapper.deleteByPrimaryKey(enid);
    }

    @Override
    public PageInfo<Enrollment> getPagedByCid(int pageNum, int pageSize, int cid) {
        PageHelper.startPage(pageNum, pageSize);
        List<Enrollment> list = enrollmentMapper.getByCid(cid);
        PageInfo<Enrollment> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public List<Enrollment> getBySlid(int slid) {
        return enrollmentMapper.getBySlid(slid);
    }
}
