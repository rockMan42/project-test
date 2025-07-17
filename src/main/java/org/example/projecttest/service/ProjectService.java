package org.example.projecttest.service;

import org.example.projecttest.common.PageUtils;
import org.example.projecttest.entity.Params;

import java.util.List;

public interface ProjectService {

    List findByPage(Params params);


}
