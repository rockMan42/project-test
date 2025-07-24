package org.example.projecttest.controller;


import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

import org.example.projecttest.entity.Params;
import org.example.projecttest.entity.ResponseSumVO;
import org.example.projecttest.entity.ResponseVO;
import org.example.projecttest.service.ProjectService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Slf4j
public class ProjectController {
    @Resource
    ProjectService projectService;


    @PostMapping("/projects")
    public List<ResponseVO> findAll(@RequestBody Params params) {
        return projectService.findAll(params);
    }

    @PostMapping("/sum")
    public ResponseSumVO getTotalSum(@RequestBody Params params) {
        return projectService.getTotalSum(params);
    }

}
