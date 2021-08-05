package DFS;
//给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
//
// 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。 
//
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,2]
//输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0]
//输出：[[],[0]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10 
// -10 <= nums[i] <= 10 
// 
// 
// 
// Related Topics 数组 回溯算法 
// 👍 523 👎 0


import java.util.ArrayList;
import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class SubsetsIi90 {

    Set<List<Integer>> res = new HashSet<>();

    // 迭代
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < (1 << len); i++) { // 用二进制码表示nums元素的选中状态
            List<Integer> cur = new ArrayList<>();
            for (int j = 0; j < len; j++) {
                if ((i & (1 << j)) != 0) {
                    cur.add(nums[j]);
                }
            }
            Collections.sort(cur);  // 要先排序才添加到集合中
            res.add(cur);
        }
        return new ArrayList<>(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
