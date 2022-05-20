package top.iaminlearn.resouce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Date: 2022/4/19 3:01
 */

@RestController
public class HelloController {

    public HelloController() {
        System.out.println("HelloController...创建了");
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }
}
