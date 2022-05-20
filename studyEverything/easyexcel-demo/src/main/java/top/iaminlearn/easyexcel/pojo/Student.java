package top.iaminlearn.easyexcel.pojo;

/**
 * Date: 2022/2/25 1:22
 */

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Student implements Serializable {
    private Integer id;
    private String name;
    private Double salary;
    private Date birthday;
}
