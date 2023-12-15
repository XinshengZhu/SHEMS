package com.nyu.shems.controller;

import com.github.pagehelper.PageInfo;
import com.nyu.shems.entity.Servicelocation;
import com.nyu.shems.service.ServicelocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("servicelocation")
public class ServicelocationController {

    @Autowired
    private ServicelocationService servicelocationService;

    @GetMapping("getAll")
    public List<Servicelocation> getAll() {
        return servicelocationService.getAll();
    }

    @PostMapping("add")
    public int addServicelocation(@RequestBody Servicelocation servicelocation) {
        return servicelocationService.insertSelective(servicelocation);
    }

    @DeleteMapping("delete/{slid}")
    public int deleteServicelocation(@PathVariable int slid) {
        return servicelocationService.deleteByPrimaryKey(slid);
    }

    @PostMapping("update")
    public int updateServicelocation(@RequestBody Servicelocation servicelocation) {
        return servicelocationService.updateByPrimaryKeySelective(servicelocation);
    }

    @GetMapping("getPaged/{cid}")
    @ResponseBody
    public PageInfo<Servicelocation> getPaged(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "5") int pageSize,
            @PathVariable int cid) {
        PageInfo<Servicelocation> pageInfo = servicelocationService.getPagedByCid(pageNum, pageSize, cid);
        return pageInfo;
    }

    @GetMapping("getByCid/{cid}")
    @ResponseBody
    public List<Servicelocation> getByCid(@PathVariable int cid) {
        return servicelocationService.getByCid(cid);

    }

}
