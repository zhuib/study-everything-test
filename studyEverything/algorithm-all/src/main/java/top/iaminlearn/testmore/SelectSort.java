package top.iaminlearn.testmore;

import java.util.Arrays;

/**
 * Date: 2021/7/2 22:12
 */
public class SelectSort {

    public static void main(String[] args) {

        int [] arr = {101, 34, 119, 1, -1, 90, 123};
        int [] arr1 = {8, 3, 2, 1, 7, 4, 6,5};
        System.out.println(Arrays.toString(arr1));
        selectSort(arr1);
        System.out.println(Arrays.toString(arr1));
    }

    public static void selectSort(int[] arr) {
        for (int i=0; i < arr.length -1; i++) {
            int minIndex = i; // 最小值索引
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
