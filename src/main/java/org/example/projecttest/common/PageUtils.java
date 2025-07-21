package org.example.projecttest.common;
import lombok.Data;
import org.example.projecttest.entity.Statistics;

import java.io.Serializable;
import java.util.List;

@Data
public class PageUtils implements Serializable {
    private static final long serialVersionUID = 1L;

    private long totalCount;

    private int pageSize;

    private int totalPage;

    private int pageIndex;

    private List list;

    private Statistics statistics;

    public PageUtils(List list, long totalCount, int pageIndex, int pageSize, Statistics statistics) {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
        this.statistics = statistics;
    }
}
