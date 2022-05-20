package top.iaminlearn.service;

/**
 * Date: 2021/7/21 20:34
 */

import top.iaminlearn.dto.AppointExecution;
import top.iaminlearn.entity.Book;

import java.util.List;

/**
 * ҵ��ӿڣ�վ��"ʹ����"�Ƕ���ƽӿ� �������棺�����������ȣ��������������ͣ�return ����/�쳣��
 */
public interface BookService {

    /**
     * ��ѯһ��ͼ��
     *
     * @param bookId
     * @return
     */
    Book getById(long bookId);

    /**
     * ��ѯ����ͼ��
     *
     * @return
     */
    List<Book> getList();

    /**
     * ԤԼͼ��
     *
     * @param bookId
     * @param studentId
     * @return
     */
    AppointExecution appoint(long bookId, long studentId);

}