package demo;

/**
 * Date: 2021/7/14 0:06
 */
public class Test {

    public static void main(String[] args) {
      Test.turnLight("��");
      Test.directLight("��");
      Test.turnAndDirectLight("��ͨ","��");
      Test.turnAndDirectLight("����","��");
    }

    public static void turnAndDirectLight(String type, String signal ) {
        if ("��ͨ".equals(type)) {
            turnLight(signal);
        } else {
            directLight(signal);
        }
    }

    public static void turnLight(String light) {
        switch (light) {
            case "��" :
                System.out.println("�����ʱ����ֹ����ͨ�У�׼����ת�䡣");
                break;
            case "��" :
                System.out.println("�Ƶ���ʱ����ֹ����ͨ�У����ѹ�ͣ���������ǰ����");
                break;
            case "��" :
                System.out.println("�̵���ʱ��������ͨ�С�");
                break;
        }
    }

    public static void directLight(String direct) {
        switch (direct) {
            case "��" :
                System.out.println("��ǰ��������ͨ�У����������ֹͨ�С�");
                break;
            case "��" :
                System.out.println("����������ͨ�У����������ֹͨ�С�");
                break;
            case "��" :
                System.out.println("���ҳ�������ͨ�У����������ֹͨ�С�");
                break;
        }
    }

}
