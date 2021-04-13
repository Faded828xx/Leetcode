package Tree;
//给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
//
// 注意：本题与 530：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bs
//t/ 相同 
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：root = [4,2,6,1,3]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：root = [1,0,48,null,null,12,49]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [2, 100] 内 
// 0 <= Node.val <= 105 
// 
// 
// 
// Related Topics 树 深度优先搜索 递归 
// 👍 138 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

class MinimumDistanceBetweenBstNodes783 {

    int pre = -100000;
    int res = 100000;

    public int minDiffInBST(TreeNode root) {
        dfs(root);
        return res;
    }

    public void dfs(TreeNode root) {
        if(root==null) return ;
        dfs(root.left);
        res = Math.min(res,root.val-pre);
        pre = root.val;
        dfs(root.right);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
