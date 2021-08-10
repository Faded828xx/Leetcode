package LinkedList;
//You are given an array of k linked-lists lists, each linked-list is sorted in
//ascending order. 
//
// Merge all the linked-lists into one sorted linked-list and return it. 
//
// 
// Example 1: 
//
// 
//Input: lists = [[1,4,5],[1,3,4],[2,6]]
//Output: [1,1,2,3,4,4,5,6]
//Explanation: The linked-lists are:
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//merging them into one sorted list:
//1->1->2->3->4->4->5->6
// 
//
// Example 2: 
//
// 
//Input: lists = []
//Output: []
// 
//
// Example 3: 
//
// 
//Input: lists = [[]]
//Output: []
// 
//
// 
// Constraints: 
//
// 
// k == lists.length 
// 0 <= k <= 10^4 
// 0 <= lists[i].length <= 500 
// -10^4 <= lists[i][j] <= 10^4 
// lists[i] is sorted in ascending order. 
// The sum of lists[i].length won't exceed 10^4. 
// 
// Related Topics 链表 分治 堆（优先队列） 归并排序 
// 👍 1440 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.PriorityQueue;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class MergeKSortedLists23 {
//    看完堆的知识点来刷的 归并是从两个有序数组中合并出一个 这里是k个
//    则在k个元素中找出最小的元素 用最小堆来维护这k个元素
//    每次移除最小的并将next加入堆中 类似归并的指针后移
    public ListNode mergeKLists(ListNode[] lists) {
//      最小堆
        PriorityQueue<ListNode> pq = new PriorityQueue<>((node1, node2) -> node1.val - node2.val);
        if(lists.length == 0) return null;
        ListNode res = new ListNode();
        ListNode ptr = res;
//        pq.addAll(Arrays.asList(lists));
        for(ListNode node : lists) {
            if(node != null)
                pq.add(node);
        }
        while(!pq.isEmpty()) {
            ListNode min = pq.poll();
            ptr.next = new ListNode(min.val);
            ptr = ptr.next;
            if(min.next != null)
                pq.add(min.next);
        }
        return res.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
