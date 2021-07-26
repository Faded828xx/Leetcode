package DP;
//给你一个数组 target ，包含若干 互不相同 的整数，以及另一个整数数组 arr ，arr 可能 包含重复元素。
//
// 每一次操作中，你可以在 arr 的任意位置插入任一整数。比方说，如果 arr = [1,4,1,2] ，那么你可以在中间添加 3 得到 [1,4,3,1,
//2] 。你可以在数组最开始或最后面添加整数。 
//
// 请你返回 最少 操作次数，使得 target 成为 arr 的一个子序列。 
//
// 一个数组的 子序列 指的是删除原数组的某些元素（可能一个元素都不删除），同时不改变其余元素的相对顺序得到的数组。比方说，[2,7,4] 是 [4,2,3,
//7,2,1,4] 的子序列（加粗元素），但 [2,4,2] 不是子序列。 
//
// 
//
// 示例 1： 
//
// 输入：target = [5,1,3], arr = [9,4,2,3,4]
//输出：2
//解释：你可以添加 5 和 1 ，使得 arr 变为 [5,9,4,1,2,3,4] ，target 为 arr 的子序列。
// 
//
// 示例 2： 
//
// 输入：target = [6,4,8,1,3,2], arr = [4,7,6,2,3,8,6,1]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// 1 <= target.length, arr.length <= 105 
// 1 <= target[i], arr[i] <= 109 
// target 不包含任何重复元素。 
// 
// Related Topics 贪心 数组 哈希表 二分查找 
// 👍 47 👎 0

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class MinimumOperationsToMakeASubsequence1713 {

    // Memory Limit Exceeded
    // dp实现最长公共子序列
    public int minOperations(int[] target, int[] arr) {
        int len1 = target.length;
        int len2 = arr.length;
        // dp[i][j]表示 target中0～i索引 与 arr中0～j索引 的最长公共子序列
        int[][] dp = new int[len1][len2];
        dp[0][0] = target[0] == arr[0] ? 1 : 0;
        // dp首行
        for(int i = 1; i < len2; i++) {
            if(dp[0][i - 1] == 1) {
                dp[0][i] = 1;
                continue;
            }
            dp[0][i] = target[0] == arr[i] ? 1 : 0;
        }
        // dp首列
        for(int i = 1; i < len1; i++) {
            if(dp[i - 1][0] == 1) {
                dp[i][0] = 1;
                continue;
            }
            dp[i][0] = arr[0] == target[i] ? 1 : 0;
        }
        for(int i = 1; i < len1; i++) {
            for(int j = 1; j < len2; j++) {
                if(target[i] == arr[j])
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        // res为需要补齐的剩余长度
        return len1 - dp[len1 - 1][len2 - 1];
    }

    // Time Limit Exceeded
    // dp实现最长递增子序列 题300
    // target中元素不同 可将每个元素用索引标识 arr中用target索引替换 不存在的元素直接忽略
    // 此时arr数组中最长的递增子序列即为 target与arr的最长公共子序列
    public int minOperations2(int[] target, int[] arr) {
        int len = target.length;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < len; i++)
            map.put(target[i], i);
        // 将arr数组映射为索引表示的list
        List<Integer> list = new ArrayList<>();
        for(int n : arr) {
            if(map.containsKey(n))
                list.add(map.get(n));
        }
        // 对list求最长递增子序列 dp O(n2)
        // dp[i]表示以list[i]结尾的最长递增子序列的长度,list[i]一定被选中
        // 此时 dp[i] = max(dp[j]) + 1, 0<=j<i, list[j]<list[i]
        // res为 max(dp[i]), 0<=i<len
        int size = list.size();
        int[] dp = new int[size];
        for(int i = 0; i < size; i++) {
            dp[i] = 1;
            int max = 0;
            for(int j = 0; j < i; j++) {
                if(list.get(j) < list.get(i))
                    max = Math.max(max, dp[j]);
            }
            dp[i] += max;
        }
        int max = 0;
        for(int n : dp)
            max = Math.max(max, n);
        // 与上同
        return len - max;
    }

    public static int minOperations3(int[] target, int[] arr) {
        int len = target.length;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < len; i++)
            map.put(target[i], i);
        // 将arr数组映射为索引表示的list
        List<Integer> list = new ArrayList<>();
        for(int n : arr) {
            if(map.containsKey(n))
                list.add(map.get(n));
        }
        // 对list求最长递增子序列 贪心+二分 O(n*lgn)
        //
        List<Integer> tail = new ArrayList<>();
        tail.add(-1);
        for(int n : list) {
            int size = tail.size();
            if(n > tail.get(size - 1))
                tail.add(n);
            else {
                int left = 1;
                int right = size - 1;
                int midN = 0;
                while(left <= right) {
                    int mid = left + (right - left) / 2;
                    midN = tail.get(mid);
                    if(midN == n)
                        break;
                    if(midN < n)
                        left = mid + 1;
                    else if(midN > n)
                        right = mid - 1;
                }
                if(midN == n) continue;
                int index = tail.get(left) > n ? left : left + 1;
                tail.set(index, n);
            }
        }
        // 最长递增子序列 长度为tail.size()-1
        return len - (tail.size() - 1);
    }

    public static void main(String[] args) {
        int[] target = new int[]{17,18,14,13,6,9,1,3,2,20};
        int[] arr = new int[]{18,15,14,6,6,13,15,20,2,6};
        System.out.println(minOperations3(target, arr));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
