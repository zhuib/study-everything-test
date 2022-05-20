package top.iaminlearn.shardingjdbc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.iaminlearn.shardingjdbc.entity.Course;

/**
 * Date: 2022/4/25 1:46
 */

@Mapper
//@Repository
public interface CourseMapper extends BaseMapper<Course> {
}
