package org.example.projecttest.controller;


import jakarta.annotation.Resource;
import org.example.projecttest.common.PageUtils;
import org.example.projecttest.entity.Params;
import org.example.projecttest.service.ProjectService;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
public class ProjectController {
    @Resource
    ProjectService projectService;

    @PostMapping("/projects")
    public List getProjects(@RequestBody Params params) {

        int start = (params.getPage()- 1) * params.getSize();

        params.setStart(start);

        params.setStartTime(params.getStartTime()+" 00:00:00");
        params.setEndTime(params.getEndTime()+" 23:59:59");
//        PageUtils pageUtils = projectService.findByPage(params);
//        return pageUtils;
        List byPage = projectService.findByPage(params);
        return byPage;
    }
}
