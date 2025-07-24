package org.example.projecttest.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.projecttest.common.ProductTypeUpgraded;
import org.example.projecttest.common.UpgradedProjectStage;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVO {


    List<ReservoirProject> reservoirProjectList;

    private Long totalCount;

    private Integer totalPage;

    private Integer currentPage;

}