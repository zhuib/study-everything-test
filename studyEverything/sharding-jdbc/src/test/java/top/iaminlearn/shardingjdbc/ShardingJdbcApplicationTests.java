package top.iaminlearn.shardingjdbc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.iaminlearn.shardingjdbc.entity.Course;
import top.iaminlearn.shardingjdbc.entity.Udict;
import top.iaminlearn.shardingjdbc.entity.User;
import top.iaminlearn.shardingjdbc.mapper.CourseMapper;
import top.iaminlearn.shardingjdbc.mapper.UdictMapper;
import top.iaminlearn.shardingjdbc.mapper.UserMapper;

//@RunWith(SpringRunner.class)
@SpringBootTest
class ShardingJdbcApplicationTests {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UdictMapper udictMapper;
    // ============================ 公共表测试 ============================
    @Test
    void addDict() {
        Udict udict = new Udict();
        udict.setUstatus("OK");
        udict.setUvalue("已启用");
        udictMapper.insert(udict);
    }

    @Test
    void deleteDict() {

        udictMapper.delete(new QueryWrapper<Udict>().eq("dictid",725353632064602113L));
    }
    // ============================ 垂直分库测试 ============================
    @Test
    void addUserDb() {
        User user = new User();
//        user.setUserId(111L);
        user.setUsername("zhangsan");
        user.setUstatus("OK");
        userMapper.insert(user);
    }

    @Test
    void findUserDb() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",111L);
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }
    // ============================ 垂直分片 ================================
    @Test
    void addCourseDb() {
        Course course = new Course();
        course.setCname("java");
        course.setUserId(111L);
        course.setCstatus("NormalDB");
//        for (int i = 0; i < 10; i++) {
            courseMapper.insert(course);
//        }
    }

    @Test
    void findCourseDb() {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("cid",725334665195421697L);
        wrapper.eq("user_id",111L);
        Course course = courseMapper.selectOne(wrapper);
        System.out.println(course);
    }
    // ============================ 水平分片测试 ============================
    @Test
    void addCourse() {
        Course course = new Course();
        course.setCname("java");
        course.setUserId(100L);
        course.setCstatus("Normal");
        for (int i = 0; i < 10; i++) {
            courseMapper.insert(course);
        }
    }

    @Test
    void findCourse() {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("cid",725184215498358784L);
        Course course = courseMapper.selectOne(wrapper);
        System.out.println(course);
    }
}
