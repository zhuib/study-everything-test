package top.iaminlearn.dto;

/**
 * Date: 2021/7/21 20:36
 */

import lombok.Data;
import lombok.ToString;

/**
 * ��װjson�������з��ؽ����ʹ����
 */

@Data
@ToString
public class Result<T> {

    private boolean success;// �Ƿ�ɹ���־

    private T data;// �ɹ�ʱ���ص�����

    private String error;// ������Ϣ

    public Result() {
    }

    // �ɹ�ʱ�Ĺ�����
    public Result(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    // ����ʱ�Ĺ�����
    public Result(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    // ʡ��getter��setter����
}