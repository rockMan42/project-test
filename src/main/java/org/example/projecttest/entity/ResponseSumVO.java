package org.example.projecttest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSumVO {

    List<ResponseVO> list;
    private Statistics statistics;

}
