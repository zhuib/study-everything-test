package top.iaminlearn.leetcode.bfs;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Date: 2022/3/18 10:17
 */
public class BFS {

    @Test
    public void bfsMindepth() {
        int[] arr = {3, 9, 20, 15, 7};
        TreeNode root = new TreeNode();
        minDepth(root);
    }

    int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        int depth = 1;

        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                TreeNode cur = q.poll();

                if (cur.left == null && cur.right == null) {
                    return depth;
                }
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
            depth++;
        }
        return depth;
    }
}


  //Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
