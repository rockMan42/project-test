package org.example.projecttest.entity;


import lombok.Data;

import java.util.Date;


@Data
public class Project {

    private int id;


    private int ProductType;


    private Date createTime;
}