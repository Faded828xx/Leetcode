package Tree;
//给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。
//
// 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
//输出：[7,4,1]
//解释：
//所求结点为与目标结点（值为 5）距离为 2 的结点，
//值分别为 7，4，以及 1
//
//
//
//注意，输入的 "root" 和 "target" 实际上是树上的结点。
//上面的输入仅仅是对这些对象进行了序列化描述。
// 
//
// 
//
// 提示： 
//
// 
// 给定的树是非空的。 
// 树上的每个结点都具有唯一的值 0 <= node.val <= 500 。 
// 目标结点 target 是树上的结点。 
// 0 <= K <= 1000. 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 320 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class AllNodesDistanceKInBinaryTree863 {
    public static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> res = new ArrayList<>();
        if (root == target) {
            add(target, k, res);
            return res;
        }
        Map<TreeNode, int[]> map = new HashMap<>();
        getDistance(root, target, map);
        add(target, k, res);
        for(TreeNode t : map.keySet()) {
            int[] arr = map.get(t);
            int d1 = arr[0];
            if(d1 == k) {
                res.add(t.val);
                continue;
            }
            int way = arr[1];
            // target在左边则next在右边 右边同理
            TreeNode next = way == 1 ? t.left : t.right;
            add(next, k - d1 - 1, res);
        }
        return res;
    }
    // key:target的所有父节点 int[0]:到target的距离 int[1]:target在该父节点的左还是右
    public static int getDistance(TreeNode root, TreeNode target, Map<TreeNode, int[]> map) {
        if (root == null) return -1;
        if (root == target) return 0;
        int l = getDistance(root.left, target, map);
        int r = getDistance(root.right, target, map);
        if (l >= 0) {
            int[] arr = new int[]{l + 1, 0};
            map.put(root, arr);
            return l + 1;
        } else if (r >= 0) {
            int[] arr = new int[]{r + 1, 1};
            map.put(root, arr);
            return r + 1;
        }
        return -1;
    }
    // 与root距离为distance的子节点加入res中
    public static void add(TreeNode root, int distance, List<Integer> res) {
        if (root == null) return;
        if (distance == 0) {
            res.add(root.val);
            return;
        }
        fun(root.left, distance, res, 1);
        fun(root.right, distance, res, 1);
    }
    public static void fun(TreeNode root, int distance, List<Integer> res, int gap) {
        if(root == null) return ;
        if(distance == gap) {
            res.add(root.val);
            return ;
        }
        fun(root.left, distance, res, gap + 1);
        fun(root.right, distance, res, gap + 1);
    }

    public static void main(String[] args) {
        TreeNode t = new TreeNode(1);
        System.out.println(distanceK(t, t, 3));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
