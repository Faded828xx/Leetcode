package BitManipulation;
//给你一个由非负整数组成的数组 nums 。另有一个查询数组 queries ，其中 queries[i] = [xi, mi] 。
//
// 第 i 个查询的答案是 xi 和任何 nums 数组中不超过 mi 的元素按位异或（XOR）得到的最大值。换句话说，答案是 max(nums[j] XOR
// xi) ，其中所有 j 均满足 nums[j] <= mi 。如果 nums 中的所有元素都大于 mi，最终答案就是 -1 。 
//
// 返回一个整数数组 answer 作为查询的答案，其中 answer.length == queries.length 且 answer[i] 是第 i 个
//查询的答案。 
//
// 
//
// 示例 1： 
//
// 输入：nums = [0,1,2,3,4], queries = [[3,1],[1,3],[5,6]]
//输出：[3,3,7]
//解释：
//1) 0 和 1 是仅有的两个不超过 1 的整数。0 XOR 3 = 3 而 1 XOR 3 = 2 。二者中的更大值是 3 。
//2) 1 XOR 2 = 3.
//3) 5 XOR 2 = 7.
// 
//
// 示例 2： 
//
// 输入：nums = [5,2,4,6,6,3], queries = [[12,4],[8,1],[6,3]]
//输出：[15,-1,5]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length, queries.length <= 105 
// queries[i].length == 2 
// 0 <= nums[j], xi, mi <= 109 
// 
// Related Topics 位运算 字典树 
// 👍 67 👎 0

import java.util.Arrays;
import java.util.Comparator;

//leetcode submit region begin(Prohibit modification and deletion)
class MaximumXorWithAnElementFromArray1707 {
    // 先通过忽略不超过mi的方式来降维 具体还是看题解吧 主要是前缀树的思想
    public static int[] maximizeXor(int[] nums, int[][] queries) {
        int len = queries.length;
        int[] res = new int[len];
        Arrays.sort(nums);
        int[][] queryArr = new int[len][3];
        for(int i=0; i<len; i++) {
            queryArr[i][0] = queries[i][0];
            queryArr[i][1] = queries[i][1];
            queryArr[i][2] = i;
        }
        Arrays.sort(queryArr, Comparator.comparingInt(ints -> ints[1]));
        int index = 0;
        Trie trie = new Trie();
        for(int[] query : queryArr) {
            int x = query[0];
            int m = query[1];
            int id = query[2];
            while(index<nums.length && nums[index]<=m) {
                trie.insert(nums[index]);
                index++;
            }
            if(index==0) {
                res[id] = -1;
            } else {
                res[id] = trie.getMaxXor(x);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5,2,4,6,6,3};
        int[][] queries = new int[][]{{12,4}, {8,1}, {6,3}};
        System.out.println(Arrays.toString(maximizeXor(nums, queries)));
    }
}

class Trie {

    static final int L = 30;
    Trie[] children = new Trie[2];

    // 向字典树中插入新值
    public void insert(int val) {
        Trie node = this;
        for (int i = L - 1; i >= 0; i--) {
            int bit = (val >> i) & 1;
            if (node.children[bit] == null)
                node.children[bit] = new Trie();
            node = node.children[bit];
        }
    }

    // 在字典树中找到与val异或后最大值 并返回异或值
    public int getMaxXor(int val) {
        int res = 0;
        Trie node = this;
        for (int i = L - 1; i >= 0; i--) {
            int bit = (val >> i) & 1;
            if (node.children[bit ^ 1] != null) {
                node = node.children[bit ^ 1];
                res |= 1 << i;
            } else {
                node = node.children[bit];
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
