package top.iaminlearn.stream.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Date: 2022/5/16 11:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode // 去重使用
public class Book {
    private Long id;
    private String name;
    private String category; // "文学,小学"
    private Integer score;
    private String intro;


}
