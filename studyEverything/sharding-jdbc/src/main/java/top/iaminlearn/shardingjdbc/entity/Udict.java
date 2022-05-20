package top.iaminlearn.shardingjdbc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Date: 2022/4/25 14:05
 */

@Data
@TableName(value = "t_udict")
public class Udict {
    private Long dictid;
    private String ustatus;
    private String uvalue;
}
