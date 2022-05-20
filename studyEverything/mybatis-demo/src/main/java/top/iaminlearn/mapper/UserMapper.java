package top.iaminlearn.mapper;

import top.iaminlearn.entity.User;

/**
 * Date: 2021/7/21 17:00
 */
public interface UserMapper {
    User selectUserById(Integer id);
}