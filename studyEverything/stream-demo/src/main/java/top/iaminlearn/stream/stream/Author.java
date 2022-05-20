package top.iaminlearn.stream.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Date: 2022/5/16 11:23
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Author {

    private Long id;
    private String name;
    private Integer age;
    private String intro;
    private List<Book> books;
}
