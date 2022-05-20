package top.iaminlearn.crawler.service;


import top.iaminlearn.crawler.pojo.JobInfoField;
import top.iaminlearn.crawler.pojo.JobResult;

import java.util.List;

/**
 * Date: 2021/5/6 20:53
 */
public interface JobRepositoryService {


    /**
     * 保存一条数据
     *
     * @param jobInfoField
     */
    void save(JobInfoField jobInfoField);

    /**
     * 批量保存数据
     *
     * @param list
     */
    void saveAll(List<JobInfoField> list);

    JobResult search(String salary, String jobaddr, String keyword, Integer page);
}