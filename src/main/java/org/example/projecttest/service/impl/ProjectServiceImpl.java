package org.example.projecttest.service.impl;

import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.projecttest.common.UpgradedProjectStage;
import org.example.projecttest.entity.Params;
import org.example.projecttest.entity.ReservoirProject;
import org.example.projecttest.repository.ProjectRepository;
import org.example.projecttest.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List findAll(Params params) {
        //分页
        PageRequest pageRequest = PageRequest.of(params.getPage(), params.getSize());

        Page<ReservoirProject> page = projectRepository.findAll(new Specification<ReservoirProject>() {
            //存放查询条件
            List<Predicate> predicates = new ArrayList<>();
            @Override
            public Predicate toPredicate(Root<ReservoirProject> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                //产品类型
                if (params.getProductType()!=null && !params.getProductType().equals("")) {
                    predicates.add(criteriaBuilder.equal(root.get("productType"), params.getProductType()));
                }

                //项目阶段
                if (params.getUpgradedProjectStage()!=null) {
                    predicates.add(criteriaBuilder.equal(root.get("upgradedProjectStage"), params.getUpgradedProjectStage()));
                }

                //开始时间
                if (params.getStartTime()!=null && !params.getStartTime().equals("")) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime"), params.getStartTime()));
                }


                //结束时间
                if (params.getEndTime()!=null && !params.getEndTime().equals("")) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createTime"), params.getEndTime()));
                }


                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }, pageRequest);

        List<ReservoirProject> list = page.getContent();
        int totalPages = page.getTotalPages();
        long totalCounts = page.getTotalElements();
        List<Object> objects = new ArrayList<>();

        objects.add(params.getPage());
        objects.add(totalPages);
        objects.add(totalCounts);
        objects.add(list);

        return objects;
    }

    @Override
    public List<ReservoirProject> findByUpgradedProjectStage(UpgradedProjectStage upgradedProjectStage) {
        return projectRepository.findByUpgradedProjectStage(upgradedProjectStage);
    }

//    @Autowired
//    ProjectRepository projectRepository;
//    @Override
//    public PageUtils findByPage(Params params) {
//        int count = projectRepository.selectCountByPage(params);
//
//        List<Project> list = null;
//
//        Statistics statistics = null;
//
//        if (count!=0) {
//            list=projectRepository.selectInfoByPage(params);
//            statistics = projectRepository.statisticsDeliveriesAndReserves(params);
//        }
//
//        return new PageUtils(list,count, params.getPage(), params.getSize(),statistics);
//
//    }


}
