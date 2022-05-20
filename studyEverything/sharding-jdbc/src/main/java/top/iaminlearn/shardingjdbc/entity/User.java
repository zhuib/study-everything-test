package top.iaminlearn.shardingjdbc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Date: 2022/4/25 13:11
 */
@Data
@TableName(value = "t_user")
public class User {

    private Long userId;
    private String username;
    private String ustatus;
}
