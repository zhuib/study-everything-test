package top.iaminlearn.exception;

/**
 * Date: 2021/7/21 20:33
 */
/**
 * ԤԼҵ���쳣
 */
public class AppointException extends RuntimeException {

    public AppointException(String message) {
        super(message);
    }

    public AppointException(String message, Throwable cause) {
        super(message, cause);
    }

}