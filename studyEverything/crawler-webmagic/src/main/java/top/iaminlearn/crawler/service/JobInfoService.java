package top.iaminlearn.crawler.service;

import org.springframework.data.domain.Page;
import top.iaminlearn.crawler.pojo.JobInfo;


import java.util.List;

/**
 * Date: 2021/5/5 17:59
 */
public interface JobInfoService {

    /**
     * 保存工作信息
     * @param jobInfo
     */
    public void save(JobInfo jobInfo);

    /**
     * 根据条件查询工作信息
     * @param jobInfo
     * @return
     */
    public List<JobInfo> findJobInfo(JobInfo jobInfo);

    Page<JobInfo> findAllPage(int page, int rows);
}
