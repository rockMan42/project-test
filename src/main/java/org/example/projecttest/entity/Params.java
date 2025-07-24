package org.example.projecttest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.projecttest.common.UpgradedProjectStage;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Params {

    int start;

    int page;

    int size;

    private UpgradedProjectStage upgradedProjectStage;

    private String startTime;

    private String endTime;

    private Integer productType;



}
