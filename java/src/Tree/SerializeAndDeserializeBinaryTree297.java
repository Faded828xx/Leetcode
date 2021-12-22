package Tree;
//Serialization is the process of converting a data structure or object into a s
//equence of bits so that it can be stored in a file or memory buffer, or transmit
//ted across a network connection link to be reconstructed later in the same or an
//other computer environment. 
//
// Design an algorithm to serialize and deserialize a binary tree. There is no r
//estriction on how your serialization/deserialization algorithm should work. You 
//just need to ensure that a binary tree can be serialized to a string and this st
//ring can be deserialized to the original tree structure. 
//
// Clarification: The input/output format is the same as how LeetCode serializes
// a binary tree. You do not necessarily need to follow this format, so please be 
//creative and come up with different approaches yourself. 
//
// 
// Example 1: 
//
// 
//Input: root = [1,2,3,null,null,4,5]
//Output: [1,2,3,null,null,4,5]
// 
//
// Example 2: 
//
// 
//Input: root = []
//Output: []
// 
//
// Example 3: 
//
// 
//Input: root = [1]
//Output: [1]
// 
//
// Example 4: 
//
// 
//Input: root = [1,2]
//Output: [1,2]
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [0, 104]. 
// -1000 <= Node.val <= 1000 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 设计 字符串 二叉树 
// 👍 715 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class SerializeAndDeserializeBinaryTree297 {

    final int INF = Integer.MAX_VALUE;

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return "[null]";
        StringBuilder res = new StringBuilder("[");
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int h = getHeight(root);
        int curH = 0;
        while(!queue.isEmpty()) {
            curH++;
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                TreeNode t = queue.poll();
                if(t.val == INF) res.append("null,");
                else res.append(t.val).append(",");
                if(curH != h) {
                    if(t.val == INF) {
//                        queue.offer(new TreeNode(INF));
//                        queue.offer(new TreeNode(INF));
                    } else {
                        if(t.left == null) queue.offer(new TreeNode(INF));
                        else queue.offer(t.left);
                        if(t.right == null) queue.offer(new TreeNode(INF));
                        else queue.offer(t.right);
                    }
                }
            }
        }
        while(res.length() >= 5 && res.charAt(res.length() - 5) == 'n') res.delete(res.length() - 5, res.length());
        if(res.charAt(res.length() - 1) == ',') res.delete(res.length() - 1, res.length());
        return res.append("]").toString();
    }
    int getHeight(TreeNode root) {
        if(root == null) return 0;
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.equals("[null]")) return null;
        String[] s = data.substring(1, data.length() - 1).split(",");
        TreeNode root = new TreeNode(Integer.parseInt(s[0]));
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int idx = 1;
        while(!queue.isEmpty()) {
            TreeNode n = queue.poll();
            if(idx < s.length && !s[idx].equals("null")) {
                n.left = new TreeNode(Integer.parseInt(s[idx]));
                queue.offer(n.left);
            }
            idx++;
            if(idx < s.length && !s[idx].equals("null")) {
                n.right = new TreeNode(Integer.parseInt(s[idx]));
                queue.offer(n.right);
            }
            idx++;
        }
        return root;
    }

//    public TreeNode deserialize(String data) {
//        if(data.equals("[null]")) return null;
//        String[] s = data.substring(1, data.length() - 1).split(",");
//        TreeNode root = new TreeNode(Integer.parseInt(s[0]));
//        dfs(root, 0, s);
//        return root;
//    }
//    void dfs(TreeNode root, int idx, String[] s) {
//        if(root == null) return ;
//        if((idx << 1) + 1 >= s.length) return ;
//        String l = s[(idx << 1) + 1];
//        String r = "";
//        if((idx << 1) + 2 < s.length)
//            r = s[(idx << 1) + 2];
//        if(!l.equals("null")) {
//            root.left = new TreeNode(Integer.parseInt(l));
//            dfs(root.left, (idx << 1) + 1, s);
//        }
//        if((idx << 1) + 2 < s.length && !r.equals("null")) {
//            root.right = new TreeNode(Integer.parseInt(r));
//            dfs(root.right, (idx << 1) + 2, s);
//        }
//    }


    public static void main(String[] args) {
        SerializeAndDeserializeBinaryTree297 serializeAndDeserializeBinaryTree297 = new SerializeAndDeserializeBinaryTree297();
        String data = "[1,2,3,null,4,5]";
        System.out.println(serializeAndDeserializeBinaryTree297.serialize(serializeAndDeserializeBinaryTree297.deserialize(data)));
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)
