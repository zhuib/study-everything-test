package top.iaminlearn.crawler.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import top.iaminlearn.crawler.dao.JobRepository;
import top.iaminlearn.crawler.pojo.JobInfoField;
import top.iaminlearn.crawler.pojo.JobResult;
import top.iaminlearn.crawler.service.JobRepositoryService;


import java.util.List;

/**
 * Date: 2021/5/6 20:53
 */
@Service
public class JobRepositoryServiceImpl implements JobRepositoryService {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public void save(JobInfoField jobInfoField) {
        this.jobRepository.save(jobInfoField);
    }

    @Override
    public void saveAll(List<JobInfoField> list) {
        this.jobRepository.saveAll(list);
    }


    @Override
    public JobResult search(String salary, String jobaddr, String keyword, Integer page) {
        //薪资处理 20-*
        int salaryMin = 0, salaryMax = 0;
        String[] salays = salary.split("-");
        //获取最小值
        if ("*".equals(salays[0])) {
            salaryMin = 0;
        } else {
            salaryMin = Integer.parseInt(salays[0]) * 10000;
        }

        //获取最大值
        if ("*".equals(salays[1])) {
            salaryMax = 900000000;
        } else {
            salaryMax = Integer.parseInt(salays[1]) * 10000;
        }

        //工作地址如果为空，就设置为*
        if (StringUtils.isBlank(jobaddr)) {
            jobaddr = "*";

            //查询关键词为空，就设置为*
        }   if (StringUtils.isBlank(keyword)) {
            keyword = "*";
        }


        //获取分页,设置每页显示30条数据
        Pageable pageable = PageRequest.of(page - 1, 30);

        //执行查询
        Page<JobInfoField> pages = this.jobRepository
                .findBySalaryMinBetweenAndSalaryMaxBetweenAndJobAddrAndJobNameAndJobInfo(salaryMin,
                        salaryMax, salaryMin, salaryMax, jobaddr, keyword, keyword, pageable);

        //封装结果
        JobResult jobResult = new JobResult();
        jobResult.setRows(pages.getContent());
        jobResult.setPageTotal(pages.getTotalPages());

        return jobResult;
    }
}
