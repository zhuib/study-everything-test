package top.iaminlearn.dto;

/**
 * Date: 2021/7/21 20:32
 */

import lombok.Data;
import lombok.ToString;
import top.iaminlearn.entity.Appointment;
import top.iaminlearn.enums.AppointStateEnum;

/**
 * 封装预约执行后结果
 */
@Data
@ToString
public class AppointExecution {

    // 图书ID
    private long bookId;

    // 秒杀预约结果状态
    private int state;

    // 状态标识
    private String stateInfo;

    // 预约成功对象
    private Appointment appointment;

    public AppointExecution() {
    }

    // 预约失败的构造器
    public AppointExecution(long bookId, AppointStateEnum stateEnum) {
        this.bookId = bookId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    // 预约成功的构造器
    public AppointExecution(long bookId, AppointStateEnum stateEnum, Appointment appointment) {
        this.bookId = bookId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.appointment = appointment;
    }

    // 省略getter和setter方法，toString方法

}
