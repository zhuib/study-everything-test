package top.iaminlearn.leetcode.test;

/**
 * Date: 2021/5/17 11:01
 */
class ListNode{
    ListNode next;
}
public class Cycle {
    public static void main(String[] args) {
        ListNode node = new ListNode();
        detectCycle(node);
    }



    static ListNode detectCycle(ListNode head) {
        ListNode fast, slow;
        fast = slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }
        // 上面的代码类似 hasCycle 函数
        slow = head;
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
