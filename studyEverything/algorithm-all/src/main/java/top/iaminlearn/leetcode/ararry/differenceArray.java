package top.iaminlearn.leetcode.ararry;

/**
 * Date: 2022/5/6 23:39
 */
public class differenceArray {
    public static void main(String[] args) {
        int[][] updates = {{1,3,2}, {2,4,3}, {0,2,-2}};
        int length = 5;
        int[] modifiedArray = getModifiedArray(length, updates);
        for (int i = 0; i < modifiedArray.length; i++) {
            System.out.print(modifiedArray[i] + " ");
        }
    }


    static int[] getModifiedArray(int length, int[][] updates) {
        // nums ��ʼ��Ϊȫ 0
        int[] nums = new int[length];
        // �����ֽⷨ
        Difference df = new Difference(nums);

        for (int[] update : updates) {
            int i = update[0];
            int j = update[1];
            int val = update[2];
            df.increment(i, j, val);
        }

        return df.result();
    }
}

// ������鹤����
class Difference {
    // �������
    private int[] diff;

    /* ����һ����ʼ���飬�������������������Ͻ��� */
    public Difference(int[] nums) {
        assert nums.length > 0;
        diff = new int[nums.length];
        // ���ݳ�ʼ���鹹��������
        diff[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            diff[i] = nums[i] - nums[i - 1];
        }
    }

    /* �������� [i, j] ���� val�������Ǹ�����*/
    public void increment(int i, int j, int val) {
        diff[i] += val;
        if (j + 1 < diff.length) {
            diff[j + 1] -= val;
        }
    }

    /* ���ؽ������ */
    public int[] result() {
        int[] res = new int[diff.length];
        // ���ݲ�����鹹��������
        res[0] = diff[0];
        for (int i = 1; i < diff.length; i++) {
            res[i] = res[i - 1] + diff[i];
        }
        return res;
    }
}

