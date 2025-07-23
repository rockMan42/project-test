package org.example.projecttest.repository;


import org.example.projecttest.common.UpgradedProjectStage;
import org.example.projecttest.entity.ReservoirProject;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface ProjectRepository extends JpaRepository<ReservoirProject,Integer>,JpaSpecificationExecutor<ReservoirProject> {
//        List<Project> selectInfoByPage(Params params);
//
//        int selectCountByPage(Params params);
//
//        Statistics statisticsDeliveriesAndReserves(Params params);

    //根据阶段条件查询
    List<ReservoirProject> findByUpgradedProjectStage(UpgradedProjectStage upgradedProjectStage);
}
