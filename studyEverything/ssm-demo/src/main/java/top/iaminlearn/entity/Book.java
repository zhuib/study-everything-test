package top.iaminlearn.entity;

import lombok.Data;
import lombok.ToString;

/**
 * Date: 2021/7/21 20:25
 */

@Data
@ToString
public class Book {

    private long bookId;// ͼ��ID

    private String name;// ͼ������

    private int number;// �ݲ�����

    // ʡ�Թ��췽����getter��setter������toString����

}