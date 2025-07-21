package org.example.projecttest.entity;

import lombok.Data;
import org.example.projecttest.common.UpgradedProjectStage;

import java.util.Date;

@Data
public class Params {

    int start;

    int page;

    int size;

    private UpgradedProjectStage upgradedProjectStage;

    private String startTime;

    private String endTime;

    private Integer productType;

}
