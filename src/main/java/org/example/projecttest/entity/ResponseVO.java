//package org.example.projecttest.entity;
//
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.example.projecttest.common.ProductTypeUpgraded;
//import org.example.projecttest.common.UpgradedProjectStage;
//
//import java.time.LocalDateTime;
//
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "pr_reservoir_project")
//public class ResponseVO {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @Column(name = "upgradedProjectStage")
//    @Enumerated(EnumType.STRING)//以字符串形式映射枚举
//    private UpgradedProjectStage upgradedProjectStage;
//
//    @Column(name = "create_time")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    private LocalDateTime createTime;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "superCustomer_id")
//    private SuperCustomer superCustomer;//一个项目对应一个客户
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id")
//    private Product product;//多个项目对应一个产品
//
//
//}