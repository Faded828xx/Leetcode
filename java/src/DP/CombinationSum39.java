package DP;//Given an array of distinct integers candidates and a target integer target, re
//turn a list of all unique combinations of candidates where the chosen numbers su
//m to target. You may return the combinations in any order. 
//
// The same number may be chosen from candidates an unlimited number of times. T
//wo combinations are unique if the frequency of at least one of the chosen number
//s is different. 
//
// It is guaranteed that the number of unique combinations that sum up to target
// is less than 150 combinations for the given input. 
//
// 
// Example 1: 
//
// 
//Input: candidates = [2,3,6,7], target = 7
//Output: [[2,2,3],[7]]
//Explanation:
//2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple ti
//mes.
//7 is a candidate, and 7 = 7.
//These are the only two combinations.
// 
//
// Example 2: 
//
// 
//Input: candidates = [2,3,5], target = 8
//Output: [[2,2,2,2],[2,3,3],[3,5]]
// 
//
// Example 3: 
//
// 
//Input: candidates = [2], target = 1
//Output: []
// 
//
// Example 4: 
//
// 
//Input: candidates = [1], target = 1
//Output: [[1]]
// 
//
// Example 5: 
//
// 
//Input: candidates = [1], target = 2
//Output: [[1,1]]
// 
//
// 
// Constraints: 
//
// 
// 1 <= candidates.length <= 30 
// 1 <= candidates[i] <= 200 
// All elements of candidates are distinct. 
// 1 <= target <= 500 
// 
// Related Topics 数组 回溯 
// 👍 1472 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class CombinationSum39 {
    // dp 写的好丑
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<List<Integer>>> dp = new ArrayList<>(target + 1);
        for(int i = 0; i <= target; i++)
            dp.add(new ArrayList<>());
        for(int cur : candidates) {
            if(cur > target) continue;
            List<List<Integer>> list = dp.get(cur);
            List<Integer> self = new ArrayList<>(){{add(cur);}};
            list.add(self);
            for(int next = cur + 1; next <= target; next++) {
                List<List<Integer>> l = dp.get(next - cur);
                if(l.size() == 0)
                    continue;
                List<List<Integer>> curL = dp.get(next);
                for(List<Integer> ls : l) {
                    List<Integer> now = new ArrayList<>(ls);
                    now.add(cur);
                    curL.add(now);
                }
            }
        }

        return dp.get(target);
    }


    // 回溯
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        Deque<Integer> cur = new ArrayDeque<>();
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, target, 0, cur, res);
        return res;
    }
    public static void backtrack(int[] candidates, int target, int index, Deque<Integer> cur, List<List<Integer>> res) {
        if(target == 0) {
            List<Integer> curL = new ArrayList<>(cur);
            res.add(curL);
            return ;
        }
        int len = candidates.length;
        if(target < 0 || index == len) return ;
        for(int i = index; i < len; i++) {
            cur.push(candidates[i]);
            backtrack(candidates, target - candidates[i], i, cur, res);
            cur.pop();
        }
    }

    public static void main(String[] args) {
        int[] candidates = new int[]{2,3,6,7};
        int target = 7;
        System.out.println(combinationSum2(candidates, target));
    }

}
//leetcode submit region end(Prohibit modification and deletion)
