package top.iaminlearn.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * Date: 2021/7/21 20:25

 * ԤԼͼ��ʵ��
 */

@Data
@ToString
public class Appointment {

    private long bookId;// ͼ��ID

    private long studentId;// ѧ��

    private Date appointTime;// ԤԼʱ��

    // ���һ�ĸ�������
    private Book book;// ͼ��ʵ��

    // ʡ�Թ��췽����getter��setter������toString����

}