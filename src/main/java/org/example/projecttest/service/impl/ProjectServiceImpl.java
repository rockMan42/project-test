package org.example.projecttest.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.projecttest.common.UpgradedProjectStage;
import org.example.projecttest.entity.*;
import org.example.projecttest.repository.ProjectRepository;
import org.example.projecttest.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.SimpleFormatter;

@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<ResponseVO> findAll(Params params) {
        //分页
        PageRequest pageRequest = PageRequest.of(params.getPage(), params.getSize());

        Page<ReservoirProject> page = projectRepository.findAll(new Specification<ReservoirProject>() {

            @Override
            public Predicate toPredicate(Root<ReservoirProject> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                if (!query.getResultType().equals(Long.class)) {
                    root.fetch("superCustomer",JoinType.LEFT);
                    root.fetch("product",JoinType.LEFT);
                } //延迟查询需要fetch关联

                List<Predicate> predicates = getSpec(root, query, criteriaBuilder, params);

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }, pageRequest);

        ResponseVO responseVO = new ResponseVO();
        responseVO.setCurrentPage(page.getNumber());
        responseVO.setTotalPage(page.getTotalPages());
        responseVO.setReservoirProjectList(page.getContent());
        responseVO.setTotalCount(page.getTotalElements());


        List<ResponseVO> list = new ArrayList<>();
        list.add(responseVO);
        return list;
    }

    @Override
    public ResponseSumVO getTotalSum(Params params) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ReservoirProject> query = builder.createQuery(ReservoirProject.class);
        Root<ReservoirProject> root = query.from(ReservoirProject.class);

        // 查询列
        CriteriaQuery<ReservoirProject> finalQuery = query.multiselect(
                builder.sum(root.get("agreementRate")).alias("agreementRate"),
                builder.sum(root.get("reservoirRate")).alias("reservoirRate")
        );

        // 查询条件
        finalQuery.where(getSpec(root, query, builder, params).toArray(new Predicate[0]));

        ReservoirProject reservoirProject = entityManager.createQuery(finalQuery).getSingleResult();

        ResponseSumVO responseSumVO = new ResponseSumVO();
        responseSumVO.setStatistics(new Statistics(reservoirProject.getAgreementRate(), reservoirProject.getReservoirRate()));
        List<ResponseVO> list = findAll(params);
        responseSumVO.setList(list);

        return responseSumVO;
    }


    //复用动态条件
    public List<Predicate> getSpec(Root<ReservoirProject> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder,Params params) {
        //存放查询条件
        List<Predicate> predicates = new ArrayList<>();

        //产品类型
        if (params.getProductType()!=null && !params.getProductType().equals("")) {
            predicates.add(criteriaBuilder.equal(root.get("productType"), params.getProductType()));
        }

        //项目阶段
        if (params.getUpgradedProjectStage()!=null) {
            predicates.add(criteriaBuilder.equal(root.get("upgradedProjectStage"), params.getUpgradedProjectStage()));
        }

        //开始时间
        if (params.getStartTime()!=null) {
            LocalDateTime startTime = LocalDateTime.parse(params.getStartTime(), formatter);//字符串解析为日期对象
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime"), startTime));
        }


        //结束时间
        if (params.getEndTime()!=null) {
            LocalDateTime endTime = LocalDateTime.parse(params.getEndTime(), formatter);
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createTime"), endTime));        }

        return predicates;
    }





}
