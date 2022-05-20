package top.iaminlearn.crawler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.iaminlearn.crawler.pojo.JobResult;
import top.iaminlearn.crawler.service.JobRepositoryService;

/**
 * Date: 2021/5/6 20:55
 */
@RestController
public class SearchController {

    @Autowired
    private JobRepositoryService jobRepositoryService;

    /**
     * 根据条件分页查询数据
     * @param salary
     * @param jobaddr
     * @param keyword
     * @param page
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public JobResult search(String salary, String jobaddr, String keyword, Integer page) {
        JobResult jobResult = this.jobRepositoryService.search(salary, jobaddr, keyword, page);
        return jobResult;
    }
}