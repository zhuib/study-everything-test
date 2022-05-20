package top.iaminlearn.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Date: 2021/7/22 1:52
 */

@Data
@ToString
public class Account implements Serializable {
    private Integer id;
    private String name;
    private Double money;
}
