package org.example.projecttest.controller;


import jakarta.annotation.Resource;
import org.example.projecttest.common.PageUtils;
import org.example.projecttest.entity.Params;
import org.example.projecttest.service.ProjectService;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProjectController {
    @Resource
    ProjectService projectService;

    @PostMapping("/projects")
    public List getProjects(@RequestBody Params params) {

        int start = (params.getPage()- 1) * params.getSize();

        params.setStart(start);

//        PageUtils pageUtils = projectService.findByPage(params);
//        return pageUtils;
        List byPage = projectService.findByPage(params);
        return byPage;
    }
}
