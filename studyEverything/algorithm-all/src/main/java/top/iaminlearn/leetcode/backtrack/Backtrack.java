package top.iaminlearn.leetcode.backtrack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Date: 2022/3/17 23:14
 */
public class Backtrack {

    List<List<Integer>> res = new LinkedList<>();

    @Test
    public void back() {
        int[] nums = {1,2,3};
        permute(nums).forEach((num)->{
            System.out.println(num);
        });
    }

    // ������ ����һ�鲻�ظ������֣��������ǵ�ȫ����
    List<List<Integer>> permute(int[] nums) {
        // ��¼[·��]
        LinkedList<Integer> track = new LinkedList<>();
        // [·��] �е�Ԫ�ػᱻ���Ϊ true �����ظ�ʹ��
        boolean[] used = new boolean[nums.length];
        //
        backtrack(nums,track,used);
        return res;
    }

    // ·������¼��track ��
    // ѡ���б� nums �в������� track ����ЩԪ��(used[i] Ϊfalse)
    // ����������nums �е�Ԫ��ȫ����track �г���
    void backtrack(int[] nums, LinkedList<Integer> track, boolean[] used) {
        // ������������
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // �ų����Ϸ���ѡ��
            if (used[i]) {
                // nums[i] �Ѿ���track �У�����
                continue;
            }
            // ��ѡ��
            track.add(nums[i]);
            used[i] = true;
            // ������һ�������
            backtrack(nums,track,used);
            // ȡ��ѡ��
            track.removeLast();
            used[i] = false;
        }
    }
}
