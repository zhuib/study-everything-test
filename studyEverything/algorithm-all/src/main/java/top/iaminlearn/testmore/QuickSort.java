package top.iaminlearn.testmore;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Date: 2021/7/3 22:37
 */
public class QuickSort {
    public static void main(String[] args) {

//        int[] arr = {-9,78,0,23,-567,70, -1,900, 4561};
//        System.out.println("arr=" + Arrays.toString(arr));
//        quickSort(arr, 0, arr.length-1);
//        System.out.println("arr=" + Arrays.toString(arr));

        //���Կ��ŵ�ִ���ٶ�
        // ����Ҫ��80000�������������
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // ����һ��[0, 8000000) ��
        }
//        System.out.println("arr=" + Arrays.toString(arr));
        System.out.println("����ǰ");
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("����ǰ��ʱ����=" + date1Str);

        quickSort(arr, 0, arr.length-1);

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("����ǰ��ʱ����=" + date2Str);
//        System.out.println("arr=" + Arrays.toString(arr));
    }


    public static void quickSort(int[] arr,int left, int right) {
        int low = left;  //
        int high = right;
        int pivot = arr[left]; // ���е�һ��Ԫ����Ϊ����

        while(low < high) { // ��ѭ�����ҵ�����ұ�����С��Ԫ��
            while (low < high && arr[high] >=pivot) {
                high --;
            }
            arr[low] = arr[high]; // ��С���������С��������

            while (low < high && arr[low] < pivot) { // ��ѭ�������Ҳ��ұ���������ȵ�Ԫ��
                low ++;
            }
            arr[high] = arr[low]; // �����������Ҳ����������

        } // ѭ������ʱlow��high �غ�

        arr[low] = pivot; // ȷ����������մ���λ��

        if (left < low -1) {
            quickSort(arr, left,low -1); // �����С�����н��еݹ�
        }
        if (high + 1 < right) {
            quickSort(arr,high + 1, right); // ���Ҳ�������н��еݹ�
        }
    }
}
