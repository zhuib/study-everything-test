package top.iaminlearn.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.iaminlearn.entity.Appointment;

/**
 * Date: 2021/7/21 20:26
 */

@Repository
public interface AppointmentDao {

    /**
     * ����ԤԼͼ���¼
     *
     * @param bookId
     * @param studentId
     * @return ���������
     */
    int insertAppointment(@Param("bookId") long bookId, @Param("studentId") long studentId);

    /**
     * ͨ��������ѯԤԼͼ���¼������Я��ͼ��ʵ��
     *
     * @param bookId
     * @param studentId
     * @return
     */
    Appointment queryByKeyWithBook(@Param("bookId") long bookId, @Param("studentId") long studentId);

}