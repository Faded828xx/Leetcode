package Tree;
//Given the root of a binary tree, return the maximum width of the given tree.
//
// The maximum width of a tree is the maximum width among all levels. 
//
// The width of one level is defined as the length between the end-nodes (the 
//leftmost and rightmost non-null nodes), where the null nodes between the end-
//nodes that would be present in a complete binary tree extending down to that level 
//are also counted into the length calculation. 
//
// It is guaranteed that the answer will in the range of a 32-bit signed 
//integer. 
//
// 
// Example 1: 
// 
// 
//Input: root = [1,3,2,5,3,null,9]
//Output: 4
//Explanation: The maximum width exists in the third level with length 4 (5,3,
//null,9).
// 
//
// Example 2: 
// 
// 
//Input: root = [1,3,2,5,null,null,9,6,null,7]
//Output: 7
//Explanation: The maximum width exists in the fourth level with length 7 (6,
//null,null,null,null,null,7).
// 
//
// Example 3: 
// 
// 
//Input: root = [1,3,2,5]
//Output: 2
//Explanation: The maximum width exists in the second level with length 2 (3,2).
//
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [1, 3000]. 
// -100 <= Node.val <= 100 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 468 👎 0


//leetcode submit region begin(Prohibit modification and deletion)


import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class MaximumWidthOfBinaryTree662 {
    public int widthOfBinaryTree(TreeNode root) {
        Queue<Integer> queue = new ArrayDeque<>();
        Map<Integer, TreeNode> map = new HashMap<>();
        queue.add(1);
        map.put(1, root);
        int res = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            int a = 0, b = 0;
            for(int i = 0; i < size; i++) {
                int first = queue.remove();
                TreeNode node = map.get(first);
                if(node.left != null) {
                    queue.add(first << 1);
                    map.put(first << 1, node.left);
                }
                if(node.right != null) {
                    queue.add((first << 1) + 1);
                    map.put((first << 1) + 1, node.right);
                }
                if(a == 0) a = first;
                b = first;
            }
            res = Math.max(res, b - a + 1);
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
