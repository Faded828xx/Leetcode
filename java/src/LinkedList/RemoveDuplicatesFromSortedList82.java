package LinkedList;//存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。
//
// 返回同样按升序排列的结果链表。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,3,4,4,5]
//输出：[1,2,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1,1,1,2,3]
//输出：[2,3]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目在范围 [0, 300] 内 
// -100 <= Node.val <= 100 
// 题目数据保证链表已经按升序排列 
// 
// Related Topics 链表 
// 👍 542 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/*
* 链表题Tip（摘自评论区）：
* 1、舍得用变量，千万别想着节省变量，否则容易被逻辑绕晕
* 2、head 有可能需要改动时，先增加一个 假head，返回的时候直接取 假head.next，这样就不需要为修改 head 增加一大堆逻辑了。
* */
//public class RemoveDuplicatesFromSortedList82 {
//    public ListNode deleteDuplicates(ListNode head) {
//        if (head == null) return null;
//        ListNode preHead = new ListNode(0, head);
//        ListNode cur = preHead;
//        while (cur.next != null && cur.next.next != null) {
//            if (cur.next.val == cur.next.next.val) {
//                int x = cur.next.val;
//                while (cur.next != null && cur.next.val == x) {
//                    cur.next = cur.next.next;
//                }
//            } else {
//                cur = cur.next;
//            }
//        }
//        return preHead.next;
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
