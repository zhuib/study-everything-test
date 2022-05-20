package top.iaminlearn.crawler.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import top.iaminlearn.crawler.pojo.JobInfo;


/**
 * Date: 2021/5/5 17:58
 */
public interface JobInfoDao extends JpaRepository<JobInfo, Long> {
}
