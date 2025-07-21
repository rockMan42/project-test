package org.example.projecttest.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.projecttest.common.PageUtils;
import org.example.projecttest.entity.Params;
import org.example.projecttest.entity.Project;
import org.example.projecttest.entity.Statistics;
import org.example.projecttest.repository.ProjectRepository;
import org.example.projecttest.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;
    @Override
    public PageUtils findByPage(Params params) {
        int count = projectRepository.selectCountByPage(params);

        List<Project> list = null;

        Statistics statistics = null;

        if (count!=0) {
            list=projectRepository.selectInfoByPage(params);
            statistics = projectRepository.statisticsDeliveriesAndReserves(params);
        }

        return new PageUtils(list,count, params.getPage(), params.getSize(),statistics);

    }
}
