package Tree;//Given the root of a binary tree and an integer targetSum, return the number of
// paths where the sum of the values along the path equals targetSum. 
//
// The path does not need to start or end at the root or a leaf, but it must go 
//downwards (i.e., traveling only from parent nodes to child nodes). 
//
// 
// Example 1: 
//
// 
//Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
//Output: 3
//Explanation: The paths that sum to 8 are shown.
// 
//
// Example 2: 
//
// 
//Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//Output: 3
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [0, 1000]. 
// -109 <= Node.val <= 109 
// -1000 <= targetSum <= 1000 
// 
// Related Topics 树 深度优先搜索 二叉树 
// 👍 1030 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.HashMap;
import java.util.Map;

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
class PathSumIII437 {
    // 递归暴力
    public int pathSum(TreeNode root, int sum) {
        if(root==null)
            return 0;
        return check(root,sum) + pathSum(root.left,sum) + pathSum(root.right,sum);
    }
    // 以root为首节点 且和为sum 的路径数
    public int check(TreeNode root, int sum){
        if(root==null) return 0;
        int next = sum - root.val;
        return (next==0 ? 1 : 0) + check(root.left,next) + check(root.right,next);
    }

    // 前缀和 + 回溯
    long target;
    int res;
    public int pathSum2(TreeNode root, int targetSum) {
        target = targetSum;
        res = 0;
        Map<Long, Integer> map = new HashMap<>();
        map.put(0L, 1);
        dfs(root, map, 0);
        return res;
    }
    public void dfs(TreeNode root, Map<Long, Integer> map, long sum) {
        if(root == null) return ;
        long cur = root.val;
        // if(cur == target) res++;
        res += map.getOrDefault(sum + cur - target, 0);
        int v = map.getOrDefault(sum + cur, 0) + 1;
        map.put(sum + cur, v);
        dfs(root.left, map, sum + cur);
        dfs(root.right, map, sum + cur);
        map.put(sum + cur, v - 1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
