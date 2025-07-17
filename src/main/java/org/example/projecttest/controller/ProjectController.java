package org.example.projecttest.controller;


import jakarta.annotation.Resource;
import org.example.projecttest.common.PageUtils;
import org.example.projecttest.entity.Params;
import org.example.projecttest.service.ProjectService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ProjectController {
    @Resource
    ProjectService projectService;

    @GetMapping("/projects")
    public List getProjects(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "30") int size) {

        int start = (page - 1) * size;

        Params params = new Params();

        params.setPage(page);
        params.setSize(size);
        params.setStart(start);

//        PageUtils pageUtils = projectService.findByPage(params);

//        return pageUtils;
        List byPage = projectService.findByPage(params);
        return byPage;
    }
}
