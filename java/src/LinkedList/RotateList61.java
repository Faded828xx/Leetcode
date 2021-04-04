package LinkedList;//给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], k = 2
//输出：[4,5,1,2,3]
// 
//
// 示例 2： 
//
// 
//输入：head = [0,1,2], k = 4
//输出：[2,0,1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 500] 内 
// -100 <= Node.val <= 100 
// 0 <= k <= 2 * 109 
// 
// Related Topics 链表 双指针 
// 👍 495 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class RotateList61 {
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null) return null;
        // get the count of total nodes
        int count = 1;
        ListNode cur = head;
        while(cur.next!=null) {
            count++;
            cur = cur.next;
        }   // cur is the rear node
        cur.next = head;
        int index = 1;
        cur = head;
        k = k % count;
        // get the node with index count-k
        while(index!=count-k) {
            index++;
            cur = cur.next;
        }
        head = cur.next;
        cur.next = null;
        return head;
    }

    // 哈希表 利用索引取节点 减少遍历次数 遇特殊情况出bug
    public ListNode rotateRightHash(ListNode head, int k) {
        if(head==null) return null;
        Map<Integer, ListNode> map = new HashMap<>();
        int index = 0;
        ListNode cur = head;
        while(cur!=null) {
            map.put(index,cur);
            index++;
            cur = cur.next;
        }   // index is the value of the count of total nodes
        k = k % (index);
        map.get(index-1).next = head;
        map.get(index-k-1).next = null;
        head = map.get(index-k);
        return head;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
