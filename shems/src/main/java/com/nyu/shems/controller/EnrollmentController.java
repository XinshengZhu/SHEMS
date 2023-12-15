package com.nyu.shems.controller;


import com.github.pagehelper.PageInfo;
import com.nyu.shems.entity.Enrollment;
import com.nyu.shems.entity.Servicelocation;
import com.nyu.shems.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("enrollment")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping("getAll")
    public List<Enrollment> getAll() {
        return enrollmentService.getAll();
    }

    @PostMapping("add")
    public int addEnrollment(@RequestBody Enrollment enrollment) {
        return enrollmentService.insertSelective(enrollment);
    }

    @DeleteMapping("delete/{enid}")
    public int deleteEnrollment(@PathVariable int enid) {
        return enrollmentService.deleteByPrimaryKey(enid);
    }

    @GetMapping("getPaged/{cid}")
    @ResponseBody
    public PageInfo<Enrollment> getPaged(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "5") int pageSize,
            @PathVariable int cid) {
        PageInfo<Enrollment> pageInfo = enrollmentService.getPagedByCid(pageNum, pageSize, cid);
        return pageInfo;
    }

    @GetMapping("getBySlid/{slid}")
    public List<Enrollment> getBySlid(@PathVariable int slid) {
        return enrollmentService.getBySlid(slid);
    }

}
