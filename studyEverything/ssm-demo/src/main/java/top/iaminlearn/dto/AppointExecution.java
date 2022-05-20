package top.iaminlearn.dto;

/**
 * Date: 2021/7/21 20:32
 */

import lombok.Data;
import lombok.ToString;
import top.iaminlearn.entity.Appointment;
import top.iaminlearn.enums.AppointStateEnum;

/**
 * ��װԤԼִ�к���
 */
@Data
@ToString
public class AppointExecution {

    // ͼ��ID
    private long bookId;

    // ��ɱԤԼ���״̬
    private int state;

    // ״̬��ʶ
    private String stateInfo;

    // ԤԼ�ɹ�����
    private Appointment appointment;

    public AppointExecution() {
    }

    // ԤԼʧ�ܵĹ�����
    public AppointExecution(long bookId, AppointStateEnum stateEnum) {
        this.bookId = bookId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    // ԤԼ�ɹ��Ĺ�����
    public AppointExecution(long bookId, AppointStateEnum stateEnum, Appointment appointment) {
        this.bookId = bookId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.appointment = appointment;
    }

    // ʡ��getter��setter������toString����

}
