package top.iaminlearn.shardingjdbc.controller;

import top.iaminlearn.shardingjdbc.entity.Course;

/**
 * Date: 2022/4/25 3:01
 */

public class HelloController {

    public String hello() {
        Course course = new Course();
        course.setUserId(100L);
        course.setCname("java");
        return "hello";
    }
}
