package top.iaminlearn.testmore;

import java.util.Arrays;

/**
 * Date: 2021/7/4 13:27
 */
public class Test {
    public static void main(String[] args) {

//        bucketTest();

        String msg = "2345";
        int index = 1;
//        String s = msg.substring(1, 2);
        char s = msg.substring(index + 1, index + 2).charAt(0);
        System.out.println(s);
    }



    public static void bucketTest() {
        int arr[] = { 53, 3, 542, 748, 14, 214};
        int[][] bucket = new int[10][arr.length];
        int[] bucketElementCounts = new int[10];

        bucket[3][bucketElementCounts[3]] = arr[0];  // bucket[3][0] = arr[0]
        System.out.println(Arrays.toString(bucketElementCounts));
        System.out.println(Arrays.toString(bucket[3]));
        bucketElementCounts[3]++;
//        bucketElementCounts[3]++;

        System.out.println(Arrays.toString(bucketElementCounts));
        System.out.println(Arrays.toString(bucket[3]));

        bucket[3][bucketElementCounts[3]] = arr[1];  // bucket[3][1] = arr[0]
        bucketElementCounts[3]++;

        bucket[3][bucketElementCounts[3]] = arr[1];  // bucket[3][1] = arr[0]
        bucketElementCounts[3]++;

        System.out.println(Arrays.toString(bucketElementCounts));
        System.out.println(Arrays.toString(bucket[3]));
    }
}
