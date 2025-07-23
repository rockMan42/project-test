package org.example.projecttest.service;

import org.example.projecttest.common.PageUtils;
import org.example.projecttest.common.UpgradedProjectStage;
import org.example.projecttest.entity.Params;
import org.example.projecttest.entity.ReservoirProject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ProjectService {

//    PageUtils findByPage(Params params);


    List findAll(Params  params);

    List<ReservoirProject> findByUpgradedProjectStage(UpgradedProjectStage upgradedProjectStage);

}
