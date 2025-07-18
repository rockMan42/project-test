package org.example.projecttest.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.projecttest.common.ProductTypeUpgraded;
import org.example.projecttest.common.UpgradedProjectStage;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    private int id;

    private ProductTypeUpgraded productTypeUpgraded;

    private Customer customer;

    private Product product;


}