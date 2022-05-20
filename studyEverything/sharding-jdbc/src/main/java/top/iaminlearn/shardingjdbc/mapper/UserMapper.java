package top.iaminlearn.shardingjdbc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.iaminlearn.shardingjdbc.entity.User;

/**
 * Date: 2022/4/25 13:12
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
