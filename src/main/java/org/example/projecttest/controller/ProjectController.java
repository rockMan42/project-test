package org.example.projecttest.controller;


import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.projecttest.common.UpgradedProjectStage;
import org.example.projecttest.entity.Params;
import org.example.projecttest.service.ProjectService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Slf4j
public class ProjectController {
    @Resource
    ProjectService projectService;


    @PostMapping("/projects")
    public List findAll(@RequestBody Params params) {
        return projectService.findAll(params);
    }

    @PostMapping("/projects/findbyUpgradedStage")
    public List findByUpgradedStage(UpgradedProjectStage upgradedProjectStage) {
        return projectService.findByUpgradedProjectStage(upgradedProjectStage);
    }

}
