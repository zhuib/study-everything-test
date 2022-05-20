package top.iaminlearn.datastruct.mytest;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date: 2021/1/25 0:56
 * 冒泡排序
 */
public class BubbleSort {

    public static void main(String[] args) {
//        int []arr = {3, 9, -1, 10, 20};

        int[] arr = new int[80000];
        for (int i =0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }

        Date date1 = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = format.format(date1);
        System.out.println(format1);
        bubbleSort(arr);
        Date date2 = new Date();
        String format2 = format.format(date2);
        System.out.println(format2);



//        System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSort(int[] arr) {
        int temp = 0;
        boolean flag = false;

        for (int i = 0; i < arr.length -1; i++) {
            for (int j =0; j< arr.length -1-i; j++) {
                if (arr[j] > arr[j+1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
//            System.out.println("第" + (i + 1) + "趟排序后的数组");
//            System.out.println(Arrays.toString(arr));
            if (!flag) {
                break;
            }else {
                flag = false;
            }
        }
    }
}
