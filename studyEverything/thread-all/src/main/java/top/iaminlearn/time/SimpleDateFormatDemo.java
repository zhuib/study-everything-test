package top.iaminlearn.time;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Date: 2021/5/17 11:24
 */
public class SimpleDateFormatDemo {

    // ���� DateTimeFormatter ����
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("mm:ss");

    public static void main(String[] args) {
        // �����̳߳�
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        // ִ�� 10 ��ʱ���ʽ��
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            // �̳߳�ִ������
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    // ����ʱ�����
                    Date date = new Date(finalI * 1000);
                    // �� Date ת���� JDK 8 �е�ʱ������ LocalDateTime
                    LocalDateTime localDateTime =
                            LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
                    // ʱ���ʽ��
                    String result = dateTimeFormatter.format(localDateTime);
                    // ��ӡ���
                    System.out.println(result);
                }
            });
        }
        // ����ִ����֮��ر��̳߳�
        threadPool.shutdown();
    }
}
