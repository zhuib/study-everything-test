package top.iaminlearn.leetcode.leetcode.editor.cn;
//给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,2,1]
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：head = [1,2]
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目在范围[1, 105] 内 
// 0 <= Node.val <= 9 
// 
//
// 
//
// 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？ 
// Related Topics 栈 递归 链表 双指针 
// 👍 1309 👎 0


public class PalindromeLinkedList {
    public static void main(String[] args) {
//        int[] x = {1,2,3,4,5,6};
        int[] x = {1, 2, 3, 4, 3, 2, 1};

        Solution solution = new PalindromeLinkedList().new Solution();
        Solution.ListNode list = solution.new ListNode().createLinkedList(x);
        System.out.println(solution.isPalindrome(list));
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        boolean isPalindrome(ListNode head) {
            ListNode slow, fast;
            slow = fast = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }

            if (fast != null)
                slow = slow.next;

            ListNode left = head;

//            System.out.println("listNode - left --->");
//            head.printLinkedList(left);
//
//            System.out.println("listNode - slow --->");
//            head.printLinkedList(slow);


            ListNode right = reverse(slow);
//            System.out.println("listNode - right --->");
//            head.printLinkedList(right);

            while (right != null) {
                if (left.val != right.val)
                    return false;
                left = left.next;
                right = right.next;
            }
            return true;
        }

        ListNode reverse(ListNode head) {
            ListNode pre = null, cur = head;
            while (cur != null) {
                ListNode next = cur.next; // 保存当前结点的下一个结点
                cur.next = pre; // 当前结点指向前一个结点
                pre = cur; //  前一个结点指向当前结点
                // 向前滚动
                cur = next;
            }
            return pre;
        }
//leetcode submit region end(Prohibit modification and deletion)

        class ListNode {
            int val;
            ListNode next;

            public ListNode() {
            }

            ListNode(int x) {
                val = x;
            }//Leetcode中链表的定义

            public ListNode createLinkedList(int[] arr) {//将输入的数组输入到链表中
                if (arr.length == 0) {
                    return null;
                }
                ListNode head = new ListNode(arr[0]);
                ListNode current = head;
                for (int i = 1; i < arr.length; i++) {//过程
                    current.next = new ListNode(arr[i]);
                    current = current.next;
                }
                printLinkedList(head);
                return head;
            }

            public void printLinkedList(ListNode head) {//将链表结果打印
                ListNode current = head;
                while (current != null) {
                    System.out.printf("%d -> ", current.val);
                    current = current.next;
                }
                System.out.println("NULL");
            }
        }

    }
}
