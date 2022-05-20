package top.iaminlearn.leetcode.test;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Date: 2021/5/17 10:57
 */
public class Advantage {

    public static void main(String[] args) {
        int[] nums1 = {12,24,8,32};
        int[] nums2 = {13,25,32,11};
        int[] count = advantageCount(nums1, nums2);
        for (int i : count) {
            System.out.println(i);
        }
    }
    static int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        // �� nums2 ��������
        PriorityQueue<int[]> maxpq = new PriorityQueue<>(
                (int[] pair1, int[] pair2) -> {
                    return pair2[1] - pair1[1];
                }
        );
        for (int i = 0; i < n; i++) {
            maxpq.offer(new int[]{i, nums2[i]});
        }
        // �� nums1 ��������
        Arrays.sort(nums1);

        // nums1[left] ����Сֵ��nums1[right] �����ֵ
        int left = 0, right = n - 1;
        int[] res = new int[n];

        while (!maxpq.isEmpty()) {
            int[] pair = maxpq.poll();
            // maxval �� nums2 �е����ֵ��i �Ƕ�Ӧ����
            int i = pair[0], maxval = pair[1];
            if (maxval < nums1[right]) {
                // ��� nums1[right] ��ʤ�� maxval���Ǿ��Լ���
                res[i] = nums1[right];
                right--;
            } else {
                // ��������Сֵ��һ�£���������
                res[i] = nums1[left];
                left++;
            }
        }
        return res;
    }
}
