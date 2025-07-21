package org.example.projecttest.repository;

import org.example.projecttest.entity.Params;
import org.example.projecttest.entity.Project;
import org.example.projecttest.entity.Statistics;

import java.util.List;


public interface ProjectRepository{
        List<Project> selectInfoByPage(Params params);

        int selectCountByPage(Params params);

        Statistics statisticsDeliveriesAndReserves(Params params);

}
