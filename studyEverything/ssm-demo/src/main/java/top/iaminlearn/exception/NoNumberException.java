package top.iaminlearn.exception;

/**
 * Date: 2021/7/21 20:33
 */
/**
 * ��治���쳣
 */
public class NoNumberException extends RuntimeException {

    public NoNumberException(String message) {
        super(message);
    }

    public NoNumberException(String message, Throwable cause) {
        super(message, cause);
    }

}