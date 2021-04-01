//给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。 
//
// 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
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
// nums 中的所有元素 互不相同 
// 
// Related Topics 位运算 数组 回溯算法 
// 👍 1103 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Subsets78 {

    List<Integer> cur = new ArrayList<>();  // o~index 的状态
    List<List<Integer>> res = new ArrayList<>();    // 目标数组

    // 回溯算法
    public List<List<Integer>> subsets(int[] nums) {
        dfs(nums,0);
        return res;
    }
    public void dfs(int[] nums, int index) {    // 确定0～index的状态
        if(index==nums.length) {    // 当前状态结束
            res.add(new ArrayList<>(cur));
            return ;
        }
        cur.add(nums[index]);
        dfs(nums,index+1);
        cur.remove(cur.size()-1);   // dfs先添加后移除 从index-1到0遍历时 此时cur的末尾元素即为nums[index]
        dfs(nums,index+1);
    }

    // 迭代算法
    public List<List<Integer>> subSets(int[] nums) {
        int len = nums.length;
        for(int i=0; i<(1<<len); i++) { // 用二进制码表示nums元素的选中状态
            List<Integer> cur = new ArrayList<>();
            for(int j=0; j<len; j++) {
                if((i&(1<<j))!=0) {
                    cur.add(nums[j]);
                }
            }
            res.add(cur);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
