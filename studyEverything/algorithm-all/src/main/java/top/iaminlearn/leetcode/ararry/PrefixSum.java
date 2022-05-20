package top.iaminlearn.leetcode.ararry;

/**
 * Date: 2022/5/6 22:43
 */
public class PrefixSum {
    public static void main(String[] args) {
        int[] arr = new int[]{-2,0,3,-5,2,-1};
        NumArray numArray = new NumArray(arr);
        System.out.println(numArray.sumRange(0, 2));
        System.out.println(numArray.sumRange(2, 5));
        System.out.println(numArray.sumRange(0, 5));
    }
}

class NumArray {

    // ǰ׺������
    private int[] preSum;

    // ����һ�����飬����ǰ׺��
    public NumArray(int[] nums) {
        // preSum[0] = 0, ���ڼ����ۼӺ�
        preSum = new int[nums.length + 1];

        // ����nums ���ۼӺ�
        for(int i = 1; i < preSum.length; i ++) {
            preSum[i] = preSum[i -1] + nums[i -1];
        }
    }

    // ��ѯ������ [left, right] ���ۼӺ�
    public int sumRange(int left, int right) {
        return preSum[right + 1] - preSum[left];
    }
}