package org.example.projecttest.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.projecttest.common.UpgradedProjectStage;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pr_reservoir_project")     //这个类保存条件 最终查询到的内容copy
public class ReservoirProject implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "upgradedProjectStage")
    @Enumerated(EnumType.STRING)//以字符串形式映射枚举
    private UpgradedProjectStage upgradedProjectStage;

    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Column(name = "product_type")
    @JsonIgnore
    private Integer productType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "superCustomer_id")
    private SuperCustomer superCustomer; //多对一

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product; //多对一

    @Column(name = "agreement_rate")
    @JsonIgnore
    private BigDecimal agreementRate;

    @Column(name = "reservoir_rate")
    @JsonIgnore
    private BigDecimal reservoirRate;

}