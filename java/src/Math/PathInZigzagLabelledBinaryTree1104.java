package Math;
//In an infinite binary tree where every node has two children, the nodes are la
//belled in row order. 
//
// In the odd numbered rows (ie., the first, third, fifth,...), the labelling is
// left to right, while in the even numbered rows (second, fourth, sixth,...), the
// labelling is right to left. 
//
// 
//
// Given the label of a node in this tree, return the labels in the path from th
//e root of the tree to the node with that label. 
//
// 
// Example 1: 
//
// 
//Input: label = 14
//Output: [1,3,4,14]
// 
//
// Example 2: 
//
// 
//Input: label = 26
//Output: [1,2,6,10,26]
// 
//
// 
// Constraints: 
//
// 
// 1 <= label <= 10^6 
// 
// Related Topics 树 数学 二叉树 
// 👍 166 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class PathInZigzagLabelledBinaryTree1104 {
    // 数学题
    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> res = new ArrayList<>();
        res.add(label);
        int p = label2Index(label);
        while((p = p / 2) != 0)
            res.add(0, index2Label(p));
        return res;
    }
    // 转顺序编号
    public int label2Index(int label) {
        int level = -1;
        for(int i = 0; i < 20; i++) {
            if(label >= (1 << i) && label < (1 << (i + 1))) {
                level = i + 1;
                break;
            }
        }
        if(level % 2 == 1) return label;
        return (1 << (level - 1)) + (1 << level) - 1 - label;
    }
    // 转label
    public int index2Label(int index) {
        int level = -1;
        for(int i = 0; i < 20; i++) {
            if(index >= (1 << i) && index < (1 << (i + 1))) {
                level = i + 1;
                break;
            }
        }
        if(level % 2 == 1) return index;
        return (1 << level) - 1 - index + (1 << (level - 1));
    }

    public static void main(String[] args) {
        PathInZigzagLabelledBinaryTree1104 pathInZigzagLabelledBinaryTree1104 = new PathInZigzagLabelledBinaryTree1104();
        System.out.println(pathInZigzagLabelledBinaryTree1104.pathInZigZagTree(26));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
