package top.iaminlearn.mapper;

import org.apache.ibatis.annotations.Param;
import top.iaminlearn.entity.Book;

import java.util.List;

/**
 * Date: 2021/7/21 20:26
 */
public interface BookDao {

    /**
     * ͨ��ID��ѯ����ͼ��
     *
     * @param id
     * @return
     */
    Book queryById(long id);

    /**
     * ��ѯ����ͼ��
     *
     * @param offset ��ѯ��ʼλ��
     * @param limit ��ѯ����
     * @return
     */
    List<Book> queryAll(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * ���ٹݲ�����
     *
     * @param bookId
     * @return ���Ӱ����������>1����ʾ���µļ�¼����
     */
    int reduceNumber(long bookId);
}