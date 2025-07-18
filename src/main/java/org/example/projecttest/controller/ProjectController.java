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
    public PageUtils getProjects(@RequestBody Params params) {

        int start = (params.getPage()- 1) * params.getSize();

        params.setStart(start);

        if (params.getStartTime()!=null) {
            params.setStartTime(params.getStartTime()+" 00:00:00");
        }
        if (params.getEndTime()!=null) {
            params.setEndTime(params.getEndTime()+" 23:59:59");
        }
        PageUtils pageUtils = projectService.findByPage(params);
        return pageUtils;

    }
}
