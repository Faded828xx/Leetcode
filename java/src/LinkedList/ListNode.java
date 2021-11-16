package LinkedList;

public class ListNode {
    public int val;
    public ListNode next;
    ListNode random;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next, ListNode random) {
        this.val = val;
        this.next = next;
        this.random = random;
    }
}
