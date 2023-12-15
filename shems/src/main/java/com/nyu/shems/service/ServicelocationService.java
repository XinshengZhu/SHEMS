package com.nyu.shems.service;

import com.github.pagehelper.PageInfo;
import com.nyu.shems.entity.Servicelocation;

import java.util.List;

public interface ServicelocationService {
    int insertSelective(Servicelocation servicelocation);

    List<Servicelocation> getAll();

    int deleteByPrimaryKey(Integer slid);

    PageInfo<Servicelocation> getPaged(int pageNum, int pageSize);

    int updateByPrimaryKeySelective(Servicelocation servicelocation);

    PageInfo<Servicelocation> getPagedByCid(int pageNum, int pageSize, int cid);

    List<Servicelocation> getByCid(int cid);
}
