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

    // 主函数 输入一组不重复的数字，返回他们的全排列
    List<List<Integer>> permute(int[] nums) {
        // 记录[路径]
        LinkedList<Integer> track = new LinkedList<>();
        // [路径] 中的元素会被标记为 true 避免重复使用
        boolean[] used = new boolean[nums.length];
        //
        backtrack(nums,track,used);
        return res;
    }

    // 路径：记录在track 中
    // 选择列表： nums 中不存在于 track 的那些元素(used[i] 为false)
    // 结束条件：nums 中的元素全都在track 中出现
    void backtrack(int[] nums, LinkedList<Integer> track, boolean[] used) {
        // 触发结束条件
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 排除不合法的选择
            if (used[i]) {
                // nums[i] 已经在track 中，跳过
                continue;
            }
            // 做选择
            track.add(nums[i]);
            used[i] = true;
            // 进入下一层决策树
            backtrack(nums,track,used);
            // 取消选择
            track.removeLast();
            used[i] = false;
        }
    }
}
