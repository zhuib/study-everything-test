package top.iaminlearn.crawler.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Date: 2021/5/5 17:57
 */
@Data
@Entity
public class JobInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String companyAddr;
    private String companyInfo;
    private String jobName;
    private String jobAddr;
    private String jobInfo;
    private Integer salaryMin;
    private Integer salaryMax;
    private String url;
    private String time;
    // 生成 get/set 方法
    // 生成 toString() 方法
}
