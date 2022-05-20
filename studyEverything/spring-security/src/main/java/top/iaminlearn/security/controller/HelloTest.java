package top.iaminlearn.security.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Date: 2022/4/6 16:42
 */

@RestController
@RequestMapping("/test")
public class HelloTest {

    @GetMapping("/hello")
    public String hello() {
        return "hello security";
    }

    @GetMapping("/index")
    public String index() {
        return "hello index";
    }

    @GetMapping("/update")
    @Secured({"ROLE_sale", "ROLE_manager"})
    public String update() {
        return "hello update";
    }

}
