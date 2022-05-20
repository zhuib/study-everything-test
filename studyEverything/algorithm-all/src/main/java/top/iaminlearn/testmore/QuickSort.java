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

        //测试快排的执行速度
        // 创建要给80000个的随机的数组
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
        }
//        System.out.println("arr=" + Arrays.toString(arr));
        System.out.println("排序前");
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);

        quickSort(arr, 0, arr.length-1);

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序前的时间是=" + date2Str);
//        System.out.println("arr=" + Arrays.toString(arr));
    }


    public static void quickSort(int[] arr,int left, int right) {
        int low = left;  //
        int high = right;
        int pivot = arr[left]; // 序列第一个元素作为枢轴

        while(low < high) { // 内循环从右到左查找比枢轴小的元素
            while (low < high && arr[high] >=pivot) {
                high --;
            }
            arr[low] = arr[high]; // 将小数放在左侧小数序列中

            while (low < high && arr[low] < pivot) { // 内循环从左到右查找比枢轴大或相等的元素
                low ++;
            }
            arr[high] = arr[low]; // 将大数放在右侧大数序列中

        } // 循环结束时low，high 重合

        arr[low] = pivot; // 确定枢轴的最终存在位置

        if (left < low -1) {
            quickSort(arr, left,low -1); // 对左侧小数序列进行递归
        }
        if (high + 1 < right) {
            quickSort(arr,high + 1, right); // 对右侧大数序列进行递归
        }
    }
}
