package Contest.Weekly_Contest;

import LinkedList.ListNode;
import Tree.TreeNode;

import java.util.TreeSet;

/**
 * @author faded828x
 * @date 2021/12/6
 */

public class Contest270 {
    // 昨天在外面玩 今天上班一上午在补题

    public int[] findEvenNumbers(int[] digits) {
        TreeSet<Integer> set = new TreeSet<>();
        for(int i = 0; i < digits.length; i++) {
            if(digits[i] == 0) continue;
            for(int j = 0; j < digits.length; j++) {
                if(j == i) continue;
                for(int k = 0; k < digits.length; k++) {
                    if(k == i || k == j || (digits[k] & 1) == 1)
                        continue;
                    set.add(digits[i] * 100 + digits[j] * 10 + digits[k]);
                }
            }
        }
        int[] res = new int[set.size()];
        int i = 0;
        for(int n : set)
            res[i++] = n;
        return res;
    }


    public ListNode deleteMiddle(ListNode head) {
        int len = getLen(head);
        if(len == 1) return null;
        if(len == 2) {
            head.next = null;
            return head;
        }
        int idx = len / 2;
        int cur = 0;
        ListNode node = head;
        while(cur != idx - 1) {
            node = node.next;
            cur++;
        }
        node.next = node.next.next;
        return head;
    }
    int getLen(ListNode head) {
        if(head == null) return 0;
        return getLen(head.next) + 1;
    }


    boolean findS = false;
    boolean findD = false;
    // nodes[i][0] 表示start节点在i节点的哪个方向 0表示不在子 1表示在左 2表示在右
    int[][] nodes;
    int startValue, destValue;
    public String getDirections(TreeNode root, int startValue, int destValue) {
        int size = getSize(root);
        this.startValue = startValue;
        this.destValue = destValue;
        nodes = new int[size + 1][3];
        initNodes(root);
        // for(int[] arr : nodes)
        //     System.out.println(Arrays.toString(arr));
        TreeNode ROOT = findRoot(root);
        return down2up(ROOT.val) + up2Down(ROOT.val);
    }
    int getSize(TreeNode root) {
        if(root == null) return 0;
        return getSize(root.left) + getSize(root.right) + 1;
    }
    void initNodes(TreeNode root) {
        if(findS && findD) return;
        if(root == null) return ;
        if(root.val == startValue) {
            findS = true;
        } else if(root.val == destValue) {
            findD = true;
        }
        if(root.left != null) {
            initNodes(root.left);
            if(root.left.val == startValue || nodes[root.left.val][0] != 0) {
                nodes[root.val][0] = 1;
            }
            if(root.left.val == destValue || nodes[root.left.val][1] != 0) {
                nodes[root.val][1] = 1;
            }
            nodes[root.left.val][2] = root.val;
        }
        if(root.right != null) {
            initNodes(root.right);
            if(root.right.val == startValue || nodes[root.right.val][0] != 0)
                nodes[root.val][0] = 2;
            if(root.right.val == destValue || nodes[root.right.val][1] != 0)
                nodes[root.val][1] = 2;
            nodes[root.right.val][2] = root.val;
        }
    }
    // 找到两节点的最近公共祖先
    TreeNode findRoot(TreeNode root) {
        if(root == null) return null;
        if(root.val == startValue || root.val == destValue) return root;
        int start = nodes[root.val][0];
        int dest = nodes[root.val][1];
        // 两节点在root的两侧
        if(start != dest) return root;
        // 两节点同在左侧
        if(start == 1) return findRoot(root.left);
        // 两节点同在右侧
        if(start == 2) return findRoot(root.right);
        return null;
    }
    // 节点startValue到root节点的向上距离
    String down2up(int root) {
        int cnt = 0;
        int cur = startValue;
        while(cur != root) {
            cur = nodes[cur][2];
            cnt++;
        }
        return "U".repeat(cnt);
    }
    // root节点到destValue节点的向下路径
    String up2Down(int root) {
        StringBuilder sb = new StringBuilder();
        int cur = destValue;
        while(cur != root) {
            cur = nodes[cur][2];
            int way = nodes[cur][1];
            if(way == 1) sb.insert(0, 'L');
            else if(way == 2) sb.insert(0, 'R');
        }
        return sb.toString();
    }
}
