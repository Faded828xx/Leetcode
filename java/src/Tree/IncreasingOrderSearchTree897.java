package Tree;
//给你一棵二叉搜索树，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
//
// 
//
// 示例 1： 
//
// 
//输入：root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
//输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
// 
//
// 示例 2： 
//
// 
//输入：root = [5,1,7]
//输出：[1,null,5,null,7]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数的取值范围是 [1, 100] 
// 0 <= Node.val <= 1000 
// 
// Related Topics 树 深度优先搜索 递归 
// 👍 166 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
// dp好几天 终于来简单题了
class IncreasingOrderSearchTree897 {
    TreeNode res = null;
    TreeNode cur = null;
    boolean isRoot = true;
    public TreeNode increasingBST(TreeNode root) {
        if(root==null) return null;
        increasingBST(root.left);
        if(isRoot) {
            res = new TreeNode(root.val);
            cur = res;
            isRoot = false;
        } else {
            cur.right = new TreeNode(root.val);
            cur = cur.right;
        }
        increasingBST(root.right);
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
