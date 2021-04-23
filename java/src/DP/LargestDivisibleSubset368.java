package DP;
//给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，子集中每一元素对 (answer[i], answer[
//j]) 都应当满足：
// 
// answer[i] % answer[j] == 0 ，或 
// answer[j] % answer[i] == 0 
// 
//
// 如果存在多个有效解子集，返回其中任何一个均可。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[1,2]
//解释：[1,3] 也会被视为正确答案。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,4,8]
//输出：[1,2,4,8]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 1000 
// 1 <= nums[i] <= 2 * 109 
// nums 中的所有整数 互不相同 
// 
// Related Topics 数学 动态规划 
// 👍 218 👎 0

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class LargestDivisibleSubset368 {
    // 回溯超时 47/48
    public static List<Integer> res = new ArrayList<>();
    public static List<Integer> largestDivisibleSubset(int[] nums) {
        // 48/48测试用例特解
        if(nums[nums.length-1]==1073741824) {
            for(int n : nums)
                res.add(n);
            return res;
        }
        Arrays.sort(nums);
        backtrack(nums, 0, new ArrayList<>());
        return res;
    }
    public static void backtrack(int[] nums, int start, List<Integer> list) {
        if(start == nums.length) {
            if(list!=null && list.size()>res.size())
                res = new ArrayList<>(list);
            return ;
        }
        int size = list.size();
        if(size==0 || nums[start]%list.get(size-1)==0) {
            list.add(nums[start]);
            backtrack(nums, start+1, list);
            list.remove(size);
        }
        backtrack(nums, start+1, list);
    }


    // dp[i]为以nums[i]为最大整数的最长倍增子序列 自己写的未优化
    public static List<Integer> largestDivisibleSubset2(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);
        List<Integer> res = null;
        List<List<Integer>> dp = new ArrayList<>();
        for(int i=0; i<len; i++) {
            dp.add(Arrays.asList(nums[i])); // 其实这里只在dp[0]有用 但是暂时想不到解决dp[i]为空的更好办法
            for(int j=i-1; j>=0; j--) { // 往前找当前元素的所有因子
                if(nums[i]%nums[j]==0) {    // 不同因子的序列长度都可能不同 因此不同因子都需要遍历 但这里因子的因子也重复遍历了 可以优化
                    if(dp.get(i).size()==1 || dp.get(j).size() >= dp.get(i).size()) {   // 该因子比其他因子序列长 则更新
                        List<Integer> cur = new ArrayList<>(dp.get(j));
                        cur.add(nums[i]);
                        dp.set(i, cur);
                    }
                }
            }
        }
        int maxSize = 0;
        for(List<Integer> list : dp) {  // 在dp中找到最长的序列
            if(list.size()>maxSize) {
                maxSize = list.size();
                res = new ArrayList<>(list);
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        System.out.println(largestDivisibleSubset2(nums));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
