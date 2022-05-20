package top.iaminlearn.crawler.pojo;

import lombok.Data;

import java.util.List;

/**
 * Date: 2021/5/6 20:55
 */

@Data
public class JobResult {

    private List<JobInfoField> rows;
    private Integer pageTotal;

    // 生成 get/set 方法
}
