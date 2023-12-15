package com.nyu.shems.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nyu.shems.entity.Servicelocation;
import com.nyu.shems.mapper.ServicelocationMapper;
import com.nyu.shems.service.ServicelocationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ServicelocationServiceImpl implements ServicelocationService {

    @Resource
    private ServicelocationMapper servicelocationMapper;

    @Override
    public int insertSelective(Servicelocation servicelocation) {
        return servicelocationMapper.insertSelective(servicelocation);
    }

    @Override
    public List<Servicelocation> getAll() {
        return servicelocationMapper.getAll();
    }

    @Override
    public int deleteByPrimaryKey(Integer slid) {
        return servicelocationMapper.deleteByPrimaryKey(slid);
    }

    @Override
    public PageInfo<Servicelocation> getPaged(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Servicelocation> list = servicelocationMapper.getAll();
        PageInfo<Servicelocation> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    @Override
    public int updateByPrimaryKeySelective(Servicelocation servicelocation) {
        return servicelocationMapper.updateByPrimaryKeySelective(servicelocation);
    }

    @Override
    public PageInfo<Servicelocation> getPagedByCid(int pageNum, int pageSize, int cid) {
        PageHelper.startPage(pageNum, pageSize);
        List<Servicelocation> list = servicelocationMapper.getByCid(cid);
        PageInfo<Servicelocation> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    @Override
    public List<Servicelocation> getByCid(int cid) {
        return servicelocationMapper.getByCid(cid);
    }
}
